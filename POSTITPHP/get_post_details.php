<?php
$response = array();
require_once './db_connect.php';
$db = new DB_CONNECT();
if (isset($_GET["pid"])) {
    $pid = $_GET['pid'];
    $result = mysql_query("SELECT * FROM posts WHERE pid = $pid");
    if (!empty($result)) {
        if (mysql_num_rows($result) > 0) {
            $result = mysql_fetch_array($result);
            $post = array();
            $post["pid"] = $result["pid"];
            $post["title"] = $result["title"];
            $post["author"] = $result["author"];
            $post["location"] = $result["location"];
            $post["description"] = $result["description"];
            
            $_POST ['year'] = $result["year"];
            $_POST ['month'] = $result["month"];;
            $_POST ['day'] = $result["day"];
            $_POST ['hour'] = $result["hour"];
            $_POST ['min'] = $result["min"];
            $_POST ['date'] = $result["date"];
            
            $post["type"] = $result["type"];
            $post["created_at"] = $result["created_at"];
            $post["updated_at"] = $result["updated_at"];
            $response["success"] = 1;
            $response["post"] = array();
            array_push($response["post"], $post);
            echo json_encode($response);
        } else {
            $response["success"] = 0;
            $response["message"] = "No post found";
            echo json_encode($response);
        }
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