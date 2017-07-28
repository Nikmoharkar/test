package com.example;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

/**
 * This Class will be responsible for automatically creating entities (Tables) and dao files.
 */
public class GreenDaoGenerator {

    public static void main(String[] args) {
        /**
         * Enter the path where the green dao package is added in our app.
         * where the DAO files will be generated
         */
        Schema schema = new Schema(1, "com.vyako.smartfactory.main.database.greendao");
        schema.enableKeepSectionsByDefault();

        addTables(schema);

        try {
            new DaoGenerator().generateAll(schema, "./app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Here we can add the tables in the database
     *
     * @param schema
     */
    private static void addTables(final Schema schema) {
        addTeamMembersEntities(schema);
    }

    /**
     * Here we can describe the columns of table
     *
     * @param schema
     * @return
     */
    private static Entity addTeamMembersEntities(final Schema schema) {
        Entity user = schema.addEntity(IConstants.TABLE_NAME);
        user.addIdProperty().primaryKey().autoincrement();

        user.addStringProperty(IConstants.COLUMN1);
        user.addStringProperty(IConstants.COLUMN2);
        user.addStringProperty(IConstants.COLUMN3);
        user.addStringProperty(IConstants.COLUMN4);
        user.addStringProperty(IConstants.COLUMN5);
        return user;
    }

}
