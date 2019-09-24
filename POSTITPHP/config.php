<?php
    // For localhost
    // define ( "DB_HOST", "localhost" );
    // define ( "DB_USER", "root" );
    // define ( "DB_PASSWORD", "Le15fe1978" );
    // define ( "DB_DATABASE", "db_post" );

    // Hosting
    //define ( "DB_HOST", "lescoccinellesmali.org");
    //define ( "DB_USER", "fhdaAdmin" );
    //define ( "DB_PASSWORD", "Le15fe1978" );
    //define ( "DB_DATABASE", $DB_DATABASE );
    //LOCALHOST
    define ( "DB_HOST", "localhost");
    define ( "DB_USER", "root" );
    define ( "DB_PASSWORD", "Le31Oct2017!" );

    echo "Database Name: " . $_POST ['domain_name'] . "\n";

    $domain_name = $_POST ['domain_name'];
    $DB_DATABASE = $domain_name."_POSTIT_DB";
    define ( "DB_DATABASE", $DB_DATABASE );


//         uid int(11) NOT NULL AUTO_INCREMENT,
//         unique_id varchar(23) NOT NULL,
//         name varchar(50) NOT NULL,
//         phone varchar(50) NOT NULL,
//         email varchar(100) NOT NULL,
//         encrypted_password varchar(80) NOT NULL,
//         salt varchar(10) NOT NULL,
//         created_at datetime DEFAULT NULL,
//         updated_at datetime DEFAULT NULL,
//         PRIMARY KEY (uid),
//         UNIQUE KEY email (email)
//        ) ENGINE=MyISAM AUTO_INCREMENT=62 DEFAULT CHARSET=latin1





    // -- Old Hosting
    // define("DB_HOST", "xldz3");
    // define("DB_USER", "lescocc1_fallaye");
    // define("DB_DATABASE", "lescocc1_DBPost");


    //$response = array ();
    //if (isset ( $_POST ['domainName'] )){
    //	$domain_name = $_POST ['domainName'];
    //    $DB_DATABASE = $domain_name."POSTIT_DB";
    //
    //
    //
    //	$result = mysql_query ( "INSERT INTO posts(title, author, location, description, year, month, day, hour, min, date, type, user_email) VALUES('$title', '$author', '$location', '$description', '$year', '$month', '$day', '$hour', '$min', '$date', $type, '$user_email')" );
    //	if ($result) {
    //		$response ["success"] = 1;
    //		$response ["message"] = "Domain successfully created.";
    //		$response ["domainName"] = $domain_name;
    //		echo json_encode ( $response );
    //	} else {
    //		$response ["result"] = $result;
    //		$response ["success"] = 0;
    //
    //		$response ["message"] = "Oops! An error occurred.";
    //		echo json_encode ( $response );
    //	}
    //} else {
    //	$response ["success"] = 0;
    //	$response ["message"] = "Required field(s) are missing";
    //	echo json_encode ( $response );
    //}
    //
    //function setUpSchool($domain){
    //
    //    return false;
    //}

    ?>