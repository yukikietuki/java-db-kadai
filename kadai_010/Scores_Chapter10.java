package kadai_010;

import java.sql.*;

import static com.mysql.cj.x.protobuf.MysqlxCrud.Order.Direction.DESC;

public class Scores_Chapter10 {

    public static void main(String[] args) {

        Connection con = null;
        Statement statement = null;


        try {
            // データベースに接続
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/challenge_java",
                    "root",
                    "YukiKietuki56"
            );

            System.out.println("データベース接続成功");

            // SQLクエリを準備
            statement = con.createStatement();
            String sql = "UPDATE scores SET score_math = '95', score_english = '80' WHERE name = '武者小路勇気';";


            // SQLクエリを実行（DBMSに送信）
            System.out.println("レコード追加:" + statement.toString());
            int rowCnt = statement.executeUpdate(sql);
            System.out.println(rowCnt + "件のレコードが追加されました");


            //並び変え
            // SQLクエリを準備
            statement = con.createStatement();
            String sql2 = "SELECT * FROM scores ORDER BY score_math DESC, score_english DESC";

            // SQLクエリを実行（DBMSに送信）
            System.out.println("データ取得を実行：" + sql2);
            ResultSet result = statement.executeQuery(sql2);
            // SQLクエリの実行結果を抽出
            while(result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                int score_math = result.getInt("score_math");
                int score_english = result.getInt("score_english");
                System.out.println(result.getRow() + "件目：生徒ID=" + id
                        + "／氏名=" + name + "／数学=" + score_math + "／英語=" + score_english);
            }

        } catch (SQLException e) {
            System.out.println("エラー発生：" + e.getMessage());
        } finally {
            // 使用したオブジェクトを解放
            if (statement != null) {
                try {statement.close();} catch (SQLException ignore) {}
            }
            if (con != null) {
                try {con.close(); } catch (SQLException ignore) {}
            }
        }

    }
}


