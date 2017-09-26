package com.ryo.metadata.web.extend;

import com.ryo.metadata.web.extend.tag.SuperTag;
import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * Created by houbinbin on 16/6/26.
 */
@Component
public class UserDirective extends SuperTag {

    @Override
    public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
        User currentUser  = getUser();
        env.setVariable("app_user", ObjectWrapper.DEFAULT_WRAPPER.wrap(currentUser));
        renderBody(env, body);
    }


    private static class User {

        private String name;

        private String avatar;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }

    /**
     * 后期抽取成为方法
     * @return
     */
    private static User getUser() {
        User user = new User();
        user.setName("Ryo");
        user.setAvatar("/static/img/avatar/default.jpg");
        return user;
    }

}
