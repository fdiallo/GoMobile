<?php
$user_email = $_POST['user_email'];
$response = array();
require_once './db_connect.php';
$db = new DB_CONNECT();
$con = $db->connect();
$result = mysqli_query($con, "SELECT * FROM posts where user_email = '$user_email' ORDER BY date DESC") or die(mysqli_error($con));
if (mysqli_num_rows($result) > 0) {
    $response["posts"] = array();
    while ($row = mysqli_fetch_array($result)) {
        $post = array();
        $post["pid"] = $row["pid"];
        $post["title"] = $row["title"];
        $post["author"] = $row["author"];
        $post["location"] = $row["location"];
        $post["description"] = $row["description"];
        $post["year"] = $row["year"];
        $post["month"] = $row["month"];
        $post["day"] = $row["day"];
        $post["hour"] = $row["hour"];
        $post["min"] = $row["min"];
        $post["date"] = $row["date"];
        $post["type"] = $row["type"];
        $post["created_at"] = $row["created_at"];
        $post["updated_at"] = $row["updated_at"];
        array_push($response["posts"], $post);
    }
    $response["success"] = 1;
    echo json_encode($response);
} else {
    $response["success"] = 0;
    $response["message"] = "No posts found";
    echo json_encode($response);
}
?>