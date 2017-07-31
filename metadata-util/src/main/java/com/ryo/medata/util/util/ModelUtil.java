package com.ryo.medata.util.util;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by bbhou on 2017/7/6.
 */
public class ModelUtil {

    /**
     * 生成valid 注解
     *
     * @param path
     */
    public static void genValid(final String path) throws IOException {
        List<String> originalList = FileUtil.getFileContentEachLine(path);
        List<String> trimList = getTrimList(originalList);
        List<FieldInfo> fieldInfoList = buildFieldInfoList(trimList);

        fillValidAnnotation(fieldInfoList);

        StringBuilder newFieldContentBuilder = new StringBuilder();
        for (FieldInfo fieldInfo : fieldInfoList) {
            newFieldContentBuilder.append(fieldInfo.toString());
        }

        String oldContent = FileUtil.getFileContent(path); //原始类的内容
        String newFieldContent = newFieldContentBuilder.toString();
        final String startStr = "extends BaseTradeBean {";
        String oldFieldContent = oldContent.substring(oldContent.indexOf(startStr)+startStr.length(), oldContent.indexOf("public String ")-"    ".length());

        String newContent = oldContent.replace(oldFieldContent, "\n"+newFieldContent);
        OutputStream outputStream = new FileOutputStream(new File(path));
        outputStream.write(newContent.getBytes());
//        FileWriter fileWriter = new FileWriter(new File(path));
//        fileWriter.write(newContent);
//        Files.write(Paths.get(path), newContent.getBytes());
//        System.out.println(newContent);
    }


    /**
     * 添加包导入。比较粗略
     * @param newContent
     * @return
     */
    private static void addValidPackageImport(String newContent) {
    }

    /**
     * 会缺少第一个
     * todo: 补全;
     * @param originalList
     * @return
     */
    private static List<String> getTrimList(List<String> originalList) {
        List<String> trimList = new LinkedList<>();
        boolean isNeed = false;
        for (String string : originalList) {
            if (string.contains("@Valid(")) {
                continue;
            }
            //private fields;
            if (string.contains("extends BaseTradeBean {")) {
                isNeed = true;
            }
            //getter and setter;
            if (string.contains("public ") && !string.contains("public class")) {
                isNeed = false;
            }

            if (isNeed
                    && !string.contains("extends BaseTradeBean {")) {
                trimList.add(string);
            }
        }
        return trimList;
    }


    private static List<FieldInfo> buildFieldInfoList(List<String> originalList) {
        List<FieldInfo> list = new LinkedList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < originalList.size(); i++) {
            String string = originalList.get(i);
            stringBuilder.append(string + "\n");
            if (StringUtil.isBlank(string)
                    && StringUtil.isNotBlank(stringBuilder.toString())) {  //换行且有内容
                String allFields = stringBuilder.toString();

                System.out.println(allFields);

                FieldInfo fieldInfo = getFieldInfo(allFields);
                list.add(fieldInfo);

                stringBuilder = new StringBuilder();    //clear;
            }
        }

        return list;
    }

    private static FieldInfo getFieldInfo(String allFields) {
        FieldInfo fieldInfo = new FieldInfo();
        String doc = "";
        String field = "";
        if(allFields.contains("private String")) {
            doc = allFields.split("private String")[0];
            field = "private String" + allFields.split("private String")[1];
        } else if(allFields.contains("private List<Underlyings>")) {
            doc = allFields.split("private List<Underlyings>")[0];
            field = "private List<Underlyings>" + allFields.split("private List<Underlyings>")[1];
        } else if(allFields.contains("private List<String>")) {
            doc = allFields.split("private List<String>")[0];
            field = "private List<String>" + allFields.split("private List<String>")[1];
        }
        fieldInfo.setDocInfo(doc);
        fieldInfo.setFieldInfo(field);
        return fieldInfo;
    }


    private static void fillValidAnnotation(List<FieldInfo> fieldInfoList) {

        for (FieldInfo fieldInfo : fieldInfoList) {
            StringBuilder validBuilder = new StringBuilder("@Valid(");
            String doc = fieldInfo.getDocInfo();
            if (doc.contains("Create Required")) {
                validBuilder.append("create = true, ");
            }
            if (doc.contains("Modify Required")) {
                validBuilder.append("modify = true, ");
            }
            if (doc.contains("Cancel Required")) {
                validBuilder.append("cancel = true, ");
            }
            if (doc.contains("Confirm Required")) {
                validBuilder.append("confirm = true, ");
            }
            if (doc.contains("Refuse Required")) {
                validBuilder.append("refuse = true, ");
            }
            if (doc.contains("Common Required")) {
                validBuilder.append("notNull = true, ");
            }
            int indexLastCommaStart = validBuilder.lastIndexOf(", ");
            int indexLastCommaEnd = indexLastCommaStart+", ".length();
            if (indexLastCommaStart > -1) {
                validBuilder.delete(indexLastCommaStart, indexLastCommaEnd);
            }
            validBuilder.append(")");
            String valid = validBuilder.toString();
            String fieldInfoContent = fieldInfo.getFieldInfo();
            if (valid.contains("true")) {
                //暂时不加 Underlyings
                if(!fieldInfoContent.contains("private List<Underlyings>")) {
                    fieldInfo.setValidInfo(valid);
                    fieldInfo.setFieldInfo("    " + fieldInfo.getFieldInfo());
                }
            }
        }

    }

    private static class FieldInfo {
        private String docInfo;
        private String fieldInfo;
        private String validInfo;
        private String dataType;

        public FieldInfo() {
        }

        public FieldInfo(String docInfo, String fieldInfo) {
            this.docInfo = docInfo;
            this.fieldInfo = fieldInfo;
        }

        public String getDocInfo() {
            return docInfo;
        }

        public void setDocInfo(String docInfo) {
            this.docInfo = docInfo;
        }

        public String getFieldInfo() {
            return fieldInfo;
        }

        public void setFieldInfo(String fieldInfo) {
            this.fieldInfo = fieldInfo;
        }

        public String getValidInfo() {
            return validInfo;
        }

        public void setValidInfo(String validInfo) {
            this.validInfo = validInfo;
        }

        public String getDataType() {
            return dataType;
        }

        public void setDataType(String dataType) {
            this.dataType = dataType;
        }

        @Override
        public String toString() {
            if (validInfo != null) {
                return docInfo + validInfo + "\n" + fieldInfo;
            }
            return docInfo + fieldInfo;
        }
    }

}
