<?php

    // Hosting
    //define ( "DB_HOST", "lescoccinellesmali.org");
    //define ( "DB_USER", "fhdaAdmin" );
    //define ( "DB_PASSWORD", "Le15fe1978" );
    //define ( "DB_DATABASE", $DB_DATABASE );

    // Hosting
   // define ( "DB_HOST", "localhost");
  //  define ( "DB_USER", "fallaye" );
   // define ( "DB_PASSWORD", "Le31oct2017!!" );
        //define ( "DB_DATABASE", $DB_DATABASE );

    //LOCALHOST
    define ( "DB_HOST", "localhost");
    define ( "DB_USER", "root" );
    define ( "DB_PASSWORD", "The31Oct2017!" );

    $con = mysqli_connect ( DB_HOST, DB_USER, DB_PASSWORD );

    if (!$con){
        echo "Connection succes!";
        die('Could not connect to server: ' . mysqli_error());
    }else{
    	echo "Connect Success!   \n";
    }
    echo "Host Name: " . $_POST ['domain_name'] . "\n";
    if (isset ( $_POST ['domain_name'] )){
        $response = array (
                           "success" => 0,
                           "error" => 0
                           );
        $domain_name = $_POST ['domain_name'];
        $DB_DATABASE = $domain_name."_POSTIT_DB";
        //$DB_DATABASE = "POSTIT_DB";

        // Selecing database
        $db_selected = mysqli_select_db ($con,  $DB_DATABASE );

        if (!$db_selected) {
            // If we couldn't, then it either doesn't exist, or we can't see it.
            //$sql = 'CREATE DATABASE '.$DB_DATABASE';
            //$sql = 'CREATE DATABASE `'.$DB_DATABASE'`';
            $sql = "CREATE DATABASE IF NOT EXISTS $DB_DATABASE";

            if (mysqli_query( $con, $sql )) {
                //Create tables
                mysqli_select_db ( $con, $DB_DATABASE );
                createTables($con);
                //echo "Database my_db created successfully\n";
                $response ["success"] = 1;
                $response ["error_msg"] = "\n  Database created successfully" . "\n";
                echo json_encode ( $response );
            } else {
                echo 'Error creating database: ' . mysqli_error($DB_DATABASE) . "\n";
                $response ["success"] = 0;
                $response ["error_msg"] = "Error occured while creating database!";
                echo json_encode ( $response );
            }
        }else{
            echo "Database " . $DB_DATABASE . " already exists";
            $response ["success"] = 1;
            $response ["error_msg"] = "Error: Database already exists!";
            echo json_encode ( $response );
        }
    }else {
        echo "Access Denied to the database";
    }

    function createTables($con){
        echo "Creating tables...";
        // Attempt create table query execution

        $sqlUsers = "CREATE TABLE IF NOT EXISTS users(
            uid INT(30) NOT NULL PRIMARY KEY AUTO_INCREMENT,
            unique_id VARCHAR(30) NOT NULL,
            name varchar(50) NOT NULL,
            phone VARCHAR(50) NOT NULL,
            email VARCHAR(100) NOT NULL,
            encrypted_password VARCHAR(80) NOT NULL,
            salt VARCHAR(10) NOT NULL,
            created_at datetime DEFAULT NULL,
            updated_at datetime DEFAULT NULL,
            UNIQUE KEY email (email)
            )";

         $sqlPosts = "CREATE TABLE IF NOT EXISTS posts (
            pid int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
            title varchar(100) NOT NULL,
            author varchar(100) NOT NULL,
            location varchar(100) NOT NULL,
            description text,
            year int(4) unsigned NOT NULL,
            month int(2) unsigned NOT NULL,
            day int(2) unsigned NOT NULL,
            hour int(2) unsigned NOT NULL,
            min int(2) unsigned NOT NULL,
            ampm varchar(2) NOT NULL,
            date datetime NOT NULL,
            type int(11) DEFAULT 0,
            created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
            updated_at datetime DEFAULT NULL,
            user_email varchar(50) NOT NULL,
            UNIQUE KEY pid (pid)
         )";



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

        if (mysqli_query($con, $sqlUsers)){
            echo "Table users created successfully";
        } else {
            echo "ERROR: Could not able to execute $sqlUsers. " . mysqli_error($con);
        }

        if (mysqli_query($con, $sqlPosts)){
            echo "Table posts created successfully";
        } else {
            echo "ERROR: Could not able to execute $sqlPosts. " . mysqli_error($con);
        }



    }

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