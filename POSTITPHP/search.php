<?php
$searchType = $_POST ['type'];
$string = $_POST ['query'];
$response = array ();
require_once './db_connect.php';
//$year = date ( "Y" );
// echo $year;
//$month = date ( "m" );
// echo $month;
//$day = date ( "d" );
// echo $day;
// $thisWeekIndex = dayofweek(date());
$db = new DB_CONNECT ();

//$result = mysql_query("SELECT CAN(*) FROM posts" ); //Note if you have a very large table you probably want to get the count instead of selecting all of the data...
$nrResults = mysql_query("SELECT CAN(*) FROM posts" );
//$nrResults = mysql_num_rows( $result );
if( $_GET['page'] ) {
	$page = (int) $_GET['page'];
} else {
	$page = 1;
}
$per_page = 5;
$offset = ($page - 1) * $per_page; //So that page 1 starts at 0, page 2 starts at 2 etc.


if ($searchType == "query") {
	// $result = mysql_query("SELECT * FROM posts WHERE LOWER(CONCAT(title, '', author, '', location, '', description, '')) LIKE '%$string%'") or die(mysql_error());
	$result = mysql_query ( "SELECT * FROM posts WHERE title LIKE '%$string%' OR author LIKE '%$string%' OR location LIKE '%$string%' OR description LIKE '%$string%' ORDER BY date DESC LIMIT $offset,$per_page " ) or die ( mysql_error () );
}
if ($searchType == "studyGroup") {
	$result = mysql_query ( "SELECT * FROM posts WHERE type = 1 ORDER BY date DESC LIMIT $offset,$per_page " ) or die ( mysql_error () );
}
if ($searchType == "event") {
	$result = mysql_query ( "SELECT * FROM posts WHERE type = 2 ORDER BY date DESC LIMIT $offset,$per_page " ) or die ( mysql_error () );
}
// if ($searchType == "today") {
// 	//$result = mysql_query ( "SELECT * FROM posts WHERE year = '$year' AND month = '$month' AND day = '$day' ORDER BY hour DESC" ) or die ( mysql_error () );
// 	$result = mysql_query ( "SELECT * FROM posts WHERE date > DATE_SUB(NOW(), INTERVAL 1 DAY) ORDER BY date DESC LIMIT $offset,$per_page ") or die ( mysql_error () );  
// 	// $result = mysql_query ( "SELECT * FROM posts WHERE year = 2015 AND month = 1 AND day = 1" ) or die ( mysql_error () );
// }
if ($searchType == "job") {
	//$result = mysql_query ( "SELECT * FROM posts WHERE year = '$year' AND month = '$month' AND day = '$day' ORDER BY hour DESC" ) or die ( mysql_error () );
	//$result = mysql_query ( "SELECT * FROM posts WHERE date > DATE_SUB(NOW(), INTERVAL 1 DAY) ORDER BY date DESC LIMIT $offset,$per_page ") or die ( mysql_error () );
	$result = mysql_query ( "SELECT * FROM posts WHERE type = 3 ORDER BY date DESC LIMIT $offset,$per_page " ) or die ( mysql_error () );
	// $result = mysql_query ( "SELECT * FROM posts WHERE year = 2015 AND month = 1 AND day = 1" ) or die ( mysql_error () );
}
if ($searchType == "housing") {
	// SELECT * FROM posts WHERE date > DATE_SUB(NOW(), INTERVAL 1 DAY) ORDER BY date DESC;
	// SELECT * FROM jokes WHERE date > DATE_SUB(NOW(), INTERVAL 1 WEEK) ORDER BY score DESC;
	// SELECT * FROM jokes WHERE date > DATE_SUB(NOW(), INTERVAL 1 MONTH) ORDER BY score DESC;
	// $result = mysql_query ( "SELECT * FROM posts WHERE year = '$year' AND month = '$month'" ) or die ( mysql_error () );
	//$result = mysql_query ( "SELECT * FROM posts WHERE date > DATE_SUB(NOW(), INTERVAL 1 WEEK) ORDER BY date DESC" ) or die ( mysql_error () );
	$result = mysql_query ( "SELECT * FROM posts WHERE type = 4 ORDER BY date DESC LIMIT $offset,$per_page " ) or die ( mysql_error () );
}
if (mysql_num_rows ( $result ) > 0) {
	$response ["posts"] = array ();
	while ( $row = mysql_fetch_array ( $result ) ) {
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
		$post ["date"] = $row ["date"];
		$post ["type"] = $row ["type"];
		$post ["created_at"] = $row ["created_at"];
		$post ["updated_at"] = $row ["updated_at"];
		array_push ( $response ["posts"], $post );
	}
	$response ["success"] = 1;
	echo json_encode ( $response );
} else {
	$response ["success"] = 0;
	$response ["message"] = "No posts found";
	$response ["data"] = $_POST ['query'];
	echo json_encode ( $response );
}
?>