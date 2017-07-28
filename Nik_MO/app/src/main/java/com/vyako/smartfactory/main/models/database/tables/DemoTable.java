package com.vyako.smartfactory.main.models.database.tables;

import com.vyako.smartfactory.main.models.database.controllers.GreenDaoController;
import com.vyako.smartfactory.main.models.database.greendao.Demo;
import com.vyako.smartfactory.main.models.database.greendao.DemoDao;

import java.util.List;


/**
 * Created by Namrata on 6/2/2017.
 * Just a Demo Table created to understand the insert, update, delete operation of GreenDao.
 */

public class DemoTable {
    private static DemoTable demoTable;
    private DemoDao demoTableDao;

    /**
     * Constructor
     */
    public DemoTable() {
        demoTableDao = GreenDaoController.getInstance().getDemoTableDao();
    }

    /**
     *
     * @return
     */
    public static DemoTable getInstance() {
        if (demoTable == null) {
            demoTable = new DemoTable();
        }
        return demoTable;
    }

    /**
     * Returns all the records of Demo Table.
     *
     * @return list of demo table's records.
     */
    public List<Demo> retrieveAllRecord() {
        List<Demo> demos = demoTableDao.loadAll();
        return demos;
    }

    /**
     * Returns all the records of Demo Table Having Some limits.
     *
     * @return list of demo table's records.
     */
    public List<Demo> retrieveRecordHavingLimits() {
        List<Demo> demos = demoTableDao.queryBuilder().limit(1000).list();
        return demos;
    }

    /**
     * Returns all the records of Demo Table in Ascending Order based in Id.
     *
     * @return list of demo table's records.
     */
    public List<Demo> retrieveRecordInAscendingOrderBasedOnId() {
        List<Demo> demos = demoTableDao.queryBuilder().orderAsc(DemoDao.Properties.Id).list();
        return demos;
    }

    /**
     * Returns all the records of Demo Table in Descending Order based in Id.
     *
     * @return list of demo table's records.
     */
    public List<Demo> retrieveRecordInDescendingOrder() {
        List<Demo> demos = demoTableDao.queryBuilder().orderDesc(DemoDao.Properties.Id).list();
        return demos;
    }

    /**
     * Return all the records of Demo Table Based on Id using single WHERE clause.
     *
     * @param id based on id, it fetches the records.
     * @return list of demo table's records.
     */
    public List<Demo> retrieveRecordUsingWhereClauseSingle(int id) {
        List<Demo> demos = demoTableDao.queryBuilder().where(DemoDao.Properties.Id.eq(id)).list();
        return demos;
    }

    /**
     * Return all the records of Demo Table Based on Id using multiple WHERE clause.
     *
     * @param id based on id, it fetches the records.
     * @return list of demo table's records.
     */
    public List<Demo> retrieveRecordUsingWhereClauseMultiple(int id, String coloumn1) {
        List<Demo> demos = null;
        demos = demoTableDao.queryBuilder().where(DemoDao.Properties.Id.eq(id), DemoDao.Properties.Coloum1.eq(coloumn1)).list();
        return demos;
    }

    /**
     * Returns Particular records in the form of Dao Based on Id.
     *
     * @param id Based on Id, fetch the record
     * @return Returns Particular records Based on Id.
     */
    public Demo retrieveRecordBasedOnId(int id) {
        Demo demo = null;
        List<Demo> demos = demoTableDao.queryBuilder().where(DemoDao.Properties.Id.eq(id)).list();
        if (demos != null && demos.size() > 0) {
            for (int i = 0; i < demos.size(); i++) {
                demo = demos.get(i);
                if (demo.getId() == id) {
                    return demo;
                }
            }
        }
        return demo;
    }

    /**
     * Returns Particular records in the form of Dao Based on Id.
     *
     * @param column1 Based on column1, fetch the record
     * @return Returns Particular records Based on Id.
     */
    public Demo retrieveRecordBasedOnColumn1(String column1) {
        Demo demo = null;
        List<Demo> demos = demoTableDao.queryBuilder().where(DemoDao.Properties.Coloum1.eq(column1)).list();
        for (int i = 0; i < demos.size(); i++) {
            demo = demos.get(i);
            if (demo.getColoum1().equals(column1)) {
                return demo;
            }
        }
        return demo;
    }

    /**
     * Retrieves particular record using WhereOr Clause.
     *
     * @param column1
     * @param column2
     * @return
     */
    public Demo retrieveRecordBasedOnColumn1AndColumn2UsingWhereOr(String column1, String column2) {
        Demo demo = null;
        List<Demo> demos = demoTableDao.queryBuilder().whereOr(DemoDao.Properties.Coloum1.eq(column1), DemoDao.Properties.Coloum2.eq(column2)).list();
        for (int i = 0; i < demos.size(); i++) {
            demo = demos.get(i);
            if (demo.getColoum1().equals(column1)) {
                return demo;
            }
        }
        return demo;
    }

    /**
     * Insert record.
     *
     * @param demo
     */
    public void insert(Demo demo) {
        demoTableDao.insert(demo);
    }

    /**
     * Insert Or Replace record.
     *
     * @param demo
     */
    public void insertOrReplace(Demo demo) {
        demoTableDao.insertOrReplace(demo);
    }

    /**
     * Insert all fields into Demo Table Based on Id.
     *
     * @param id
     * @param demo
     */
    public void insert(int id, Demo demo) {
        List<Demo> list = demoTableDao.queryBuilder().where(DemoDao.Properties.Id.eq(id)).list();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Demo demo1 = list.get(i);
                demoTableDao.insert(demo1);
            }
        }
    }

    /**
     * Insert single field into Demo Table based on Id.
     *
     * @param id
     * @param column1
     */
    public void insert(int id, String column1) {
        List<Demo> list = demoTableDao.queryBuilder().where(DemoDao.Properties.Id.eq(id)).list();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Demo demo = list.get(i);
                demo.setColoum1(column1);
                demoTableDao.insert(demo);
            }
        }
    }

    /**
     * Update single record of Demo Table.
     *
     * @param demo
     */
    public void update(Demo demo) {
        demoTableDao.update(demo);
    }

    /**
     * Update single field of single record.
     *
     * @param id
     * @param column1
     */
    public void updateSingleRecord(int id, String column1) {
        List<Demo> demos = demoTableDao.queryBuilder().where(DemoDao.Properties.Id.eq(id)).list();
        for (int i = 0; i < demos.size(); i++) {
            Demo demo = demos.get(i);
            demo.setColoum1(column1);
            demoTableDao.update(demo);
        }
    }

    /**
     * Update Multilpe fields of Single records.
     *
     * @param id
     * @param column1
     */
    public void updateMultipleRecords(int id, String column1) {
        List<Demo> demos = demoTableDao.loadAll();
        for (int i = 0; i < demos.size(); i++) {
            if (demos.get(i).getId() == id) {
                Demo demo = demos.get(i);
                demo.setColoum1(column1);
                demoTableDao.update(demo);
            }
        }
    }

    /**
     * Update single column of all records with same value.
     *
     * @param column1
     */
    public void updateAllRecords(String column1) {
        List<Demo> demos = demoTableDao.loadAll();
        for (int i = 0; i < demos.size(); i++) {
            Demo demo = demos.get(i);
            demo.setColoum1(column1);
            demoTableDao.update(demo);
        }
    }

    /**
     * Delete all records.
     */
    public void deleteAllRecords() {
        demoTableDao.deleteAll();
    }

    /**
     * Delete single record.
     *
     * @param demo
     */
    public void deleteSingleRecord(Demo demo) {
        demoTableDao.delete(demo);
    }

    /**
     * Delete Single record based on Id.
     *
     * @param id
     */
    public void deleteRecordBasedOnId(int id) {
        List<Demo> list = demoTableDao.queryBuilder().where(DemoDao.Properties.Id.eq(id)).list();
        for (int i = 0; i < list.size(); i++) {
            Demo demo = list.get(i);
            demoTableDao.delete(demo);
        }
    }

    /**
     * Delete all records Based on Id.
     *
     * @param id
     */
    public void deleteAllRecordBasedOnId(int id) {
        List<Demo> list = demoTableDao.loadAll();
        for (int i = 0; i < list.size(); i++) {
            Demo demo = list.get(i);
            if (demo.getId() == id) {
                demoTableDao.delete(demo);
            }
        }
    }
}
