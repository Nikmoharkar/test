package com.vyako.smartfactory.main.models.database.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig demoDaoConfig;

    private final DemoDao demoDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        demoDaoConfig = daoConfigMap.get(DemoDao.class).clone();
        demoDaoConfig.initIdentityScope(type);

        demoDao = new DemoDao(demoDaoConfig, this);

        registerDao(Demo.class, demoDao);
    }
    
    public void clear() {
        demoDaoConfig.clearIdentityScope();
    }

    public DemoDao getDemoDao() {
        return demoDao;
    }

}
