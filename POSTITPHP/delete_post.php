<?php
$response = array();
if (isset($_POST['pid'])) {
    $pid = $_POST['pid'];
    require_once './db_connect.php';
    $db = new DB_CONNECT();
    $con = $db->connect();
    $result = mysqli_query($con, "DELETE FROM posts WHERE pid = $pid");
    if (mysqli_affected_rows($con) > 0) {
        $response["success"] = 1;
        $response["message"] = "Post successfully deleted";
        echo json_encode($response);
    } else {
        $response["success"] = 0;
        $response["message"] = "No post found";
        echo json_encode($response);
    }
} else {
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
    echo json_encode($response);
}
?>