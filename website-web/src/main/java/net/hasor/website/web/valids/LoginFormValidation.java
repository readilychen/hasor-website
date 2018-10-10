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
package net.hasor.website.web.valids;
import net.hasor.web.valid.ValidInvoker;
import net.hasor.web.valid.ValidStrategy;
import net.hasor.web.valid.Validation;
import net.hasor.website.web.forms.LoginForm;
import org.apache.commons.lang3.StringUtils;
/**
 *
 * @version : 2016年1月1日
 * @author 赵永春 (zyc@hasor.net)
 */
public class LoginFormValidation implements Validation<LoginForm> {
    @Override
    public ValidStrategy doValidation(String validType, LoginForm dataForm, ValidInvoker errors) {
        if (StringUtils.isBlank(dataForm.getLogin())) {
            errors.addError("login", "帐号不能为空！");
        }
        if (StringUtils.isBlank(dataForm.getPassword())) {
            errors.addError("password", "密码不能为空！");
        }
        return ValidStrategy.DEFAULT_CONTINUE;
    }
}