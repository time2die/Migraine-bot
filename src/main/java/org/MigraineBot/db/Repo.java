package org.MigraineBot.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Repo {

    private static final String INSERT_IF_NEED = "INSERT INTO bot_users (tg_id) VALUES (?) ON CONFLICT DO NOTHING;";
    private static final String INSERT_ANSWER = """
                INSERT INTO bot_answers (tg_id, question_id, answer)
                VALUES (?, ?, ?)
                ON CONFLICT DO NOTHING;
            """.trim();
    private static final String SQL_SELECT_ANSWERS = """
            SELECT answer FROM (
                                   SELECT answer, count(*) as times
                                   FROM bot_answers as ba
                                   WHERE tg_id = ? AND question_id = ?
                                   GROUP BY answer
                                   ORDER BY times DESC
                        
            ) as res;                 
            """.trim();

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

    public Repo() {
        DbPool.getInstance().getDbConn();
    }

    public static void main(String[] args) {
        for (Long id : Repo.GetUsers()) {
            System.out.println(id);
            CreateUser(id);

            var a = Answers(id, 1);
            for (String i : a) {
                System.out.println(i);
            }
            System.out.println();
        }
    }

    public static void SafeAnswer(long tgId, long qId, String answer) {
        try {
            PreparedStatement updateUsers = DbPool.getInstance().getDbConn().prepareStatement(INSERT_ANSWER);
            updateUsers.setLong(1, tgId);
            updateUsers.setLong(2, qId);
            updateUsers.setString(3, answer);
            var dbId = updateUsers.executeUpdate();
            System.out.println("Write to answer db, db_id:" + dbId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static List<String> Answers(long tgId, long qId) {
        var results = new ArrayList<String>();
        try {
            PreparedStatement selectStm = DbPool.getInstance().getDbConn().prepareStatement(SQL_SELECT_ANSWERS);
            selectStm.setLong(1, tgId);
            selectStm.setLong(2, qId);
            ResultSet resultSet = selectStm.executeQuery();

            while (resultSet.next()) {
                String answer = resultSet.getString("answer");
                results.add(answer);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return Collections.EMPTY_LIST;
        }
        return results;
    }
}

