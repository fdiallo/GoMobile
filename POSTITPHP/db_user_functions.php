<?php
class DB_Functions {
	private $db;
	function __construct() {
		require_once './db_connect.php';
		$this->db = new DB_Connect();
		$this->db->connect();
	}
	function __destruct() {
	}
	public function storeUser($name, $phone, $email, $password) {
	    $con = $this->db->connect ();
		$uuid = substr(uniqid ( '', true ), 0, 10);
		if (! isset ( $password ) || trim ( $password ) === '')
			return false;
		$hash = $this->hashSSHA ( $password );
		$encrypted_password = $hash ["encrypted"];
		$salt = $hash ["salt"];
		$result = mysqli_query ($con, "INSERT INTO users(unique_id, name, phone, email, encrypted_password, salt, created_at) VALUES('$uuid', '$name', '$phone', '$email', '$encrypted_password', '$salt', NOW())" );
		if ($result) {
			$last_record_id = mysqli_insert_id ($con);
			$selectedUser = mysqli_query ($con, "SELECT * FROM users WHERE uid = $last_record_id" );
			return mysqli_fetch_array ( $selectedUser );
		} else {
			return false;
		}
	}
	public function updateUser($userTrackByEmail, $phone, $password) {
		// $uuid = uniqid ( '', true );
		$con = $this->db->connect ();
		if (! isset ( $password ) || trim ( $password ) === '')
			return false;
		$hash = $this->hashSSHA ( $password );
		$encrypted_password = $hash ["encrypted"];
		$salt = $hash ["salt"];
		$result = mysqli_query ($con, "UPDATE users SET phone='$phone',
				encrypted_password='$encrypted_password', salt='$salt',
				updated_at=NOW() where email='$userTrackByEmail'" );
		
		if ($result) {
			return true;
		} else {
			return false;
		}
	}
	public function deleteUser($userTrackByEmail) {
	    $con = $this->db->connect ();
		$result = mysqli_query ($con, "DELETE FROM users where email='$userTrackByEmail'" );
		if ($result) {
			return true;
		} else {
			return false;
		}
	}
	public function getUserByEmailAndPassword($email, $password) {
	 $con = $this->db->connect ();
		$result = mysqli_query ( $con, "SELECT * FROM users WHERE email = '$email'" ) or die ( mysqli_error ($con) );
		$no_of_rows = mysqli_num_rows ( $result );
		if ($no_of_rows > 0) {
			$result = mysqli_fetch_array ( $result );
			$salt = $result ['salt'];
			$encrypted_password = $result ['encrypted_password'];
			$hash = $this->checkhashSSHA ( $salt, $password );
			if ($encrypted_password == $hash) {
				return $result;
			}
		} else {
			return false;
		}
	}
	public function isUserExisted($email) {
		$result = mysqli_query ($this->db->connect (), "SELECT email from users WHERE email = '$email'" );
		$no_of_rows = mysqli_num_rows ( $result );
		if ($no_of_rows > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public function getUserByEmail($email){
	    $con = $this->db->connect ();
		$result = mysqli_query($con, "SELECT * FROM users where email = '$email'");
		$no_of_rows = mysqli_num_rows ( $result );
		if ($no_of_rows > 0) {
			return true;
		} else {
			return false;
		}
	}
	public function hashSSHA($password) {
		$salt = sha1 ( rand () );
		$salt = substr ( $salt, 0, 10 );
		$encrypted = base64_encode ( sha1 ( $password . $salt, true ) . $salt );
		$hash = array (
				"salt" => $salt,
				"encrypted" => $encrypted 
		);
		return $hash;
	}
	public function checkhashSSHA($salt, $password) {
		$hash = base64_encode ( sha1 ( $password . $salt, true ) . $salt );
		return $hash;
	}
}
?>