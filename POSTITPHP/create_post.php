<?php
$response = array ();
// if (isset ( $_POST ['title'] ) && isset ( $_POST ['author'] ) && isset ( $_POST ['location'] ) && isset ( $_POST ['description'] ) && isset ( $_POST ['year'] ) && isset ( $_POST ['month'] ) && isset ( $_POST ['day'] ) && isset ( $_POST ['hour'] ) && isset ( $_POST ['min'] ) && isset ( $_POST ['date'] ) && isset ( $_POST ['study_group'] )) {
if (isset ( $_POST ['title'] ) && isset ( $_POST ['author'] ) && isset ( $_POST ['location'] ) && isset ( $_POST ['description'] ) && isset ( $_POST ['year'] ) && isset ( $_POST ['month'] ) && isset ( $_POST ['day'] ) && isset ( $_POST ['hour'] ) && isset ( $_POST ['min'] ) && isset ( $_POST ['type'] )){
	$title = $_POST ['title'];
	$author = $_POST ['author'];
	$location = $_POST ['location'];
	$description = $_POST ['description'];
	$year = $_POST ['year'];
	$month = $_POST ['month'];
	$day = $_POST ['day'];
	$hour = $_POST ['hour'];
	$min = $_POST ['min'];
	$ampm = $_POST ['ampm'];
    $date = $_POST ['date'];
	$type = $_POST ['type'];
	$user_email = $_POST ['user_email'];
	require_once './db_connect.php';
	$db = new DB_CONNECT ();
	$con = $db->connect();
	$result = mysqli_query ($con, "INSERT INTO posts(title, author, location, description, year, month, day, hour, min, ampm, date, type, user_email) VALUES('$title', '$author', '$location', '$description', '$year', '$month', '$day', '$hour', '$min', '$ampm', '$date', $type, '$user_email')" );
	if ($result) {
		$response ["success"] = 1;
		$response ["message"] = "Post successfully created.";
		$response ["year"] = $year;
		$response ["month"] = $month;
		$response ["day"] = $day;
		$response ["hour"] = $hour;
		$response ["min"] = $min;
		$response ["date"] = $date;
		$response ["type"] = $type;
		$response ["user_email"] = $user_email;
		echo json_encode ( $response );
	} else {
		$response ["result"] = $result;
		$response ["title"] = $title;
		$response ["author"] = $author;
		$response ["location"] = $location;
		$response ["description"] =$description;
		$response ["year"] = $year;
		$response ["month"] = $month;
		$response ["day"] = $day;
		$response ["hour"] = $hour;
		$response ["min"] = $min;
		$response ["date"] = $date;
		$response ["type"] = $type;
		$response ["user_email"] = $user_email;
		$response ["success"] = 0;
		$response ["message"] = "Oops! An error occurred.";
		echo json_encode ( $response );
	}
} else {
	$response ["success"] = 0;
	$response ["message"] = "Required field(s) are missing";
	echo json_encode ( $response );
}
?>