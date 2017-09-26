package com.ryo.metadata.web.extend.tag;

import freemarker.core.Environment;
import freemarker.template.*;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * <p>Equivalent to {@link org.apache.shiro.web.tags.SecureTag}</p>
 */
public abstract class SuperTag implements TemplateDirectiveModel {
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        verifyParameters(params);
        render(env, params, body);
    }

    public abstract void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException;

    protected String getParam(Map params, String name) {
        Object value = params.get(name);

        if (value instanceof SimpleScalar) {
            return ((SimpleScalar)value).getAsString();
        }
        
        return null;
    }

//    protected Subject getSubject() {
//        return SecurityUtils.getSubject();
//    }

    protected void verifyParameters(Map params) throws TemplateModelException {
    }

    protected void renderBody(Environment env, TemplateDirectiveBody body) throws IOException, TemplateException {
        if (body != null) {
            body.render(env.getOut());
        }
    }

    protected String getPrincipalProperty(Object principal, String property) throws TemplateModelException {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(principal.getClass());

            // Loop through the properties to get the string value of the specified property
            for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
                if (propertyDescriptor.getName().equals(property)) {
                    Object value = propertyDescriptor.getReadMethod().invoke(principal, (Object[]) null);

                    return String.valueOf(value);
                }
            }

            // property not found, throw
            throw new TemplateModelException("Property ["+property+"] not found in principal of type ["+principal.getClass().getName()+"]");
        } catch (IllegalAccessException | IntrospectionException | InvocationTargetException e) {
            throw new TemplateModelException("Error reading property ["+property+"] from principal of type ["+principal.getClass().getName()+"]", e);
        }
    }
}
