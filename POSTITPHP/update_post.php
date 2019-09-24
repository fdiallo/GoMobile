<?php
$response = array();
if (isset($_POST['pid']) && isset($_POST['title']) && isset($_POST['author'])
		&& isset($_POST['location']) && isset($_POST['description'])
		&& isset($_POST['year']) && isset($_POST['month']) && isset($_POST['day'])
		&& isset($_POST['hour']) && isset($_POST['min'])
		&& isset($_POST['type'])) {
    $pid = $_POST['pid'];
    $title = $_POST['title'];
    $author = $_POST['author'];
    $location = $_POST['location'];
    $description = $_POST['description'];
    $year = $_POST['year'];
    $month = $_POST['month'];
    $day = $_POST['day'];
    $hour = $_POST['hour'];
    $min = $_POST['min'];
    $date = $_POST['date'];
    //$updated_at = $_POST['date'];
    $type = $_POST['type'];
    $user_email = $_POST['user_email'];
    require_once './db_connect.php';
    $db = new DB_CONNECT();
    $con = $db->connect();
    //$result = mysql_query("UPDATE posts SET title = '$title', author = '$author', location = '$location', description = '$description', year = '$year', month = '$month', day = '$day', hour = '$hour', min = '$min', study_group = '$study_group' WHERE pid = '$pid'");
    //$result = mysql_query("UPDATE posts SET title = '$title', author = '$author', location = '$location', description = '$description', year = '$year', month = '$month', day = '$day', hour = '$hour', min = '$min', type = '$type' WHERE pid = '$pid'");
    $result = mysqli_query($con, "UPDATE posts SET title = '$title', author = '$author', location = '$location', description = '$description', year = '$year', month = '$month', day = '$day', hour = '$hour', min = '$min', date = '$date', type = '$type' WHERE pid = '$pid' && user_email = '$user_email'");
    if ($result) {
        $response["success"] = 1;
        $response["message"] = "Post successfully updated.";
        echo json_encode($response);
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
    	$response ["updated_at"] = $date;
    	//$response ["user_email"] = $user_email;
    	$response ["success"] = 0;

    	$response ["message"] = "Oops! An error occurred.";
    	echo json_encode ( $response );
    }
} else {
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
    echo json_encode($response);
}
?>