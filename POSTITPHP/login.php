<?php
if (isset ( $_POST ['tag'] ) && $_POST ['tag'] != '') {
	$tag = $_POST ['tag'];
	require_once './db_user_functions.php';
	$db = new DB_Functions ();
	$response = array (
			"tag" => $tag,
			"success" => 0,
			"error" => 0 
	);
	if ($tag == 'login') {
		$email = $_POST ['email'];
		$password = $_POST ['password'];
		$user = $db->getUserByEmailAndPassword ( $email, $password );
		if ($user != false) {
			$response ["success"] = 1;
			$response ["error_msg"] = "You have successfully logged in!";
			$response ["uid"] = $user ["unique_id"];
			////$response ["user"] ["name"] = $user ["name"];
			$response ["user"] ["email"] = $user ["email"];
			$response ["user"] ["password"] = $user ["password"];
			$response ["user"] ["created_at"] = $user ["created_at"];
			$response ["user"] ["updated_at"] = $user ["updated_at"];
			echo json_encode ( $response );
		} else {
			$response ["error"] = 1;
			$response ["error_msg"] = "Incorrect email and/or password!";
			echo json_encode ( $response );
		}
	} else if ($tag == 'register') {
		$name= $_POST ['name'];
		$email = $_POST ['email'];
		$phone = $_POST ['phone'];
		$password = $_POST ['password'];
		if ($db->isUserExisted ($email )) {
			$response ["error"] = 2;
			$response ["error_msg"] = "User already exists";
			echo json_encode ( $response );
		} else {
			$user = $db->storeUser ( $name, $phone, $email, $password );
			if ($user) {
				$response ["success"] = 1;
				$response ["error_msg"] = "You have successfully registerd!";
				$response ["uid"] = $user ["unique_id"];
				$response ["user"] ["name"] = $user ["name"];
				$response ["user"] ["email"] = $user ["email"];
				$response ["user"] ["phone"] = $user ["phone"];
				$response ["user"] ["created_at"] = $user ["created_at"];
				$response ["user"] ["updated_at"] = $user ["updated_at"];
				echo json_encode ( $response );
			} else {
				$response ["error"] = 1;
				$response ["error_msg"] = "Error occured in Registartion";
				$response ["user"] ["name"] = $name;
				$response ["user"] ["email"] = $email;
				//$response ["user"] ["phone"] = $phone;
				echo json_encode ( $response );
			}
		}
	} else if ($tag == 'update') {
		$userTrackByEmail = $_POST ['userTrackByEmail'];
		$phone = $_POST ['phone'];
		$password = $_POST ['password'];
		$userExist = $db->getUserByEmail($userTrackByEmail);
		if ($userExist) {
			$userUpdate = $db->updateUser ($userTrackByEmail, $phone, $password);
			if ($userUpdate) {
				$response ["success"] = 1;
				$response ["error_msg"] = "You have successfully update your Account!";
				$response ["uid"] = $userUpdate ["uid"];
				$response ["user"] ["name"] = $userUpdate ["name"];
				$response ["user"] ["email"] = $userUdate ["email"];
				$response ["user"] ["phone"] = $userUdate ["phone"];
				$response ["user"] ["updated_at"] = $userUpdate ["updated_at"];
				echo json_encode ( $response );
			} else {
				$response ["error"] = 1;
				$response ["error_msg"] = "Error occured in Updating Registartion";
				echo json_encode ( $response );
			}
		} else {
			$response ["error"] = 1;
			$response ["error_msg"] = "Passwords do not match!";
			echo json_encode ( $response );
		}
	}else if ($tag == 'delete') {
		$userTrackByEmail = $_POST ['userTrackByEmail'];
		//$name = $_POST ['name'];
		//$password = $_POST ['password'];
		$userExist = $db->getUserByEmail($userTrackByEmail);
		if ($userExist) {
			$userUpdate = $db->deleteUser ($userTrackByEmail);
			if ($userUpdate) {
				$response ["success"] = 1;
				//$response ["uid"] = $userUpdate ["uid"];
				//$response ["user"] ["name"] = $userUpdate ["name"];
				//$response ["user"] ["email"] = $userUdate ["email"];
				//$response ["user"] ["updated_at"] = $userUpdate ["updated_at"];
				$response ["error_msg"] = "Your account was successfully deleted!";
				echo json_encode ( $response );
			} else {
				$response ["error"] = 1;
				$response ["error_msg"] = "Error occured in deleting user";
				echo json_encode ( $response );
			}
		} else {
			$response ["error"] = 1;
			$response ["error_msg"] = "Incorrect email or password!";
			echo json_encode ( $response );
		}
	}else {
		echo "Invalid Request";
	}
} else {
	echo "Access Denied to DB";
}
?>