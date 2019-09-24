<?php
$response = array ();
require_once './db_connect.php';
$db = new DB_CONNECT ();
$con = $db->connect();

//$result = mysql_query("SELECT CAN(*) FROM posts" ); //Note if you have a very large table you probably want to get the count instead of selecting all of the data...
//$nrResults = mysqli_query($con, "SELECT CAN(*) FROM posts" );
//$nrResults = mysql_num_rows( $result );
if( $_POST['page'] ) {
	$page = (int) $_POST['page'];
} else {
	$page = 1;
}
$per_page = 5;
$offset = ($page - 1) * $per_page; //So that page 1 starts at 0, page 2 starts at 2 etc.
//$result = mysql_query("SELECT * FROM users LIMIT $offset,$per_page");

$result = mysqli_query ($con, "SELECT * FROM posts ORDER BY date DESC LIMIT $offset, $per_page " ) or die ( mysqli_error ($con) );
if (mysqli_num_rows ( $result ) > 0) {
	$response ["posts"] = array ();
	while ( $row = mysqli_fetch_array ( $result ) ) {
		$post = array ();
		$post ["pid"] = $row ["pid"];
		$post ["title"] = $row ["title"];
		$post ["author"] = $row ["author"];
		$post ["location"] = $row ["location"];
		$post ["description"] = $row ["description"];
		$post ["year"] = $row ["year"];
		$post ["month"] = $row ["month"];
		$post ["day"] = $row ["day"];
		$post ["hour"] = $row ["hour"];
		$post ["min"] = $row ["min"];
		$post ["ampm"] = $row ["ampm"];
		$post ["date"] = $row ["date"];
		$post ["type"] = $row ["type"];
		$post ["created_at"] = $row ["created_at"];
		$post ["updated_at"] = $row ["updated_at"];
		array_push ( $response ["posts"], $post );
	}
	$response ["success"] = 1;
	$response ["more_posts"] = "yes";
	$response ["page"] = $page;
	echo json_encode ( $response );
} else {
	$response ["success"] = 0;
	$response ["message"] = "No posts found";
	$response ["more_posts"] = "no";
	echo json_encode ( $response );
}
?>