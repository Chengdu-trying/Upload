
<!DOCTYPE html>
<html>
<body>
<?php 
	session_start();
	$username = $_SESSION['user'];
	echo "Name=". $_SESSION['user'].userName;
	echo "Name=";
?>

</body>
</html>