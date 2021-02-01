package org.MigraineBot.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Repo {

    public static void main(String[] args) {
        for (Long id : Repo.GetUsers()) {
            System.out.println(id);
            CreateUser(id);
        }
    }

    private static String INSERT_IF_NEED = "INSERT INTO bot_users (tg_id) VALUES (?) ON CONFLICT DO NOTHING;";

    public static void CreateUser(long id) {
        try {
            PreparedStatement updateUsers = DbPool.getInstance().getDbConn().prepareStatement(INSERT_IF_NEED);
            updateUsers.setLong(1, id);
            var dbId = updateUsers.executeUpdate();
            System.out.println("Write or update db, db_id:" + dbId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return;
        }
    }


    private static final String SQL_SELECT = "SELECT tg_id FROM bot_users";

    public static Set<Long> GetUsers() {
        var results = new HashSet<Long>();
        try {
            DbPool.getInstance().getDbConn().prepareStatement(SQL_SELECT);

            ResultSet resultSet = DbPool.getInstance().getDbConn().prepareStatement(SQL_SELECT).executeQuery();

            while (resultSet.next()) {
                long tgId = resultSet.getLong("tg_id");
                results.add(tgId);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return Collections.EMPTY_SET;
        }

        return results;
    }
}
