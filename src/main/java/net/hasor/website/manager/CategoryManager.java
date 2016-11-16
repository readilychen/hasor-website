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
package net.hasor.website.manager;
import net.hasor.website.datadao.ContentCategoryDAO;
import net.hasor.website.domain.ContentCategoryDO;
import net.hasor.core.Inject;
import net.hasor.core.Singleton;
import org.more.bizcommon.log.LogUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
/**
 * 内容分类Manager
 * @version : 2016年1月10日
 * @author 赵永春(zyc@hasor.net)
 */
@Singleton
public class CategoryManager {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Inject
    private ContentCategoryDAO contentCategoryDAO;
    //
    /**根据用户ID,查询用户下所有分类,同时进行排序。*/
    public List<ContentCategoryDO> queryListByUser(long userId) {
        try {
            List<ContentCategoryDO> infoListDO = contentCategoryDAO.queryListByUserID(userId);
            if (infoListDO == null || infoListDO.isEmpty()) {
                return new ArrayList<ContentCategoryDO>(0);
            }
            return infoListDO;
        } catch (Exception e) {
            logger.error(LogUtils.create("ERROR_001_0001")//
                    .addLog("userId", userId) //
                    .logException(e).toJson());
            return new ArrayList<ContentCategoryDO>(0);
        }
    }
}