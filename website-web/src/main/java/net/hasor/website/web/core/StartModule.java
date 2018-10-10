/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.hasor.website.web.core;
import net.hasor.utils.convert.ConverterUtils;
import net.hasor.utils.convert.convert.DateConverter;
import net.hasor.web.WebApiBinder;
import net.hasor.web.WebModule;
import net.hasor.web.annotation.MappingTo;
import net.hasor.website.core.RootModule;
import net.hasor.website.domain.beans.AppConstant;
import net.hasor.website.login.oauth.OAuthModule;

import java.util.Date;
import java.util.Set;
/**
 *
 * @version : 2015年12月25日
 * @author 赵永春 (zyc@hasor.net)
 */
public class StartModule extends WebModule {
    @Override
    public void loadModule(WebApiBinder apiBinder) throws Throwable {
        //
        apiBinder.installModule(new OAuthModule());
        apiBinder.installModule(new RootModule(true));
        //
        apiBinder.setEncodingCharacter("utf-8", "utf-8");
        String contextPath = apiBinder.getServletContext().getContextPath();
        apiBinder.bindType(String.class).nameWith(AppConstant.VAR_CONTEXT_PATH).toInstance(contextPath);
        //
        apiBinder.suffix("htm").bind(MyFreemarkerRender.class);//设置 Freemarker 渲染器
        // 扫描所有带有 @MappingTo 特征类
        Set<Class<?>> aClass = apiBinder.findClass(MappingTo.class);
        // 对 aClass 集合进行发现并自动配置控制器
        apiBinder.loadMappingTo(aClass);
        //
        // .Webs
        apiBinder.jeeFilter("/*").through(0, new JumpFilter());
        //
        DateConverter converter = new DateConverter();
        converter.setPatterns(new String[] { "yyyy-MM-dd", "hh:mm:ss", "yyyy-MM-dd hh:mm:ss" });
        ConverterUtils.register(converter, Date.class);
    }
}
