package selekh;

public class DBQueries {

	public static final String GET_ALL_USERS = "SELECT user.id,user.name"
			+ "FROM user;";

	public static final String GET_BY_ID = "SELECT user.id,user.name"
			+ "FROM user "
			+ "where id = ?;";

	public static final String INSERT_USER ="INSERT INTO `ex_db`.`user` "
			+ "(`id`,`name`) "
			+ "VALUES (?,?);";

	public static final String INSERT_MEMBER ="INSERT INTO `ex_db`.`member` "
			+ "(`id`,`name`, `pass`) "
			+ "VALUES (?,?,?);";

	public static final String DELETE_USER = "DELETE FROM `ex_db`.`user` WHERE `id`=?;";
	public static final String UPDATE_USER ="UPDATE `ex_db`.`user` SET `name`=? WHERE `id`=?;";
	public static final String LOGIN ="SELECT member.id,member.pass FROM member where name = ? and pass = ?;";
}
