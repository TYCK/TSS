<?php
error_reporting(E_ERROR);	
include_once __DIR__.'/db_connect.php';
$db = new DBConnector();
$response = array();

if(isset($_GET['CRN']) )
{
	$CRN= $_GET['CRN'];
	$query = "";

	$query = "select * from courses where CRN = $CRN";
	
	

	$result = mysql_query($query) or die(mysql_error());
	if($result)
{
	if(mysql_num_rows($result) > 0)
	{
		$response['courses'] = array();
		while($row = mysql_fetch_array($result))
		{
			array_push($response['courses'],$row);
		}
		$response['success'] = 1;
	}
}
}
if(isset($_GET['CODE']) )
{
	$CODE= $_GET['CODE'];
	$query = "";

	$query = "select * from courses where Code =$CODE";
	
	

	$result = mysql_query($query) or die(mysql_error());
	if($result)
{
	if(mysql_num_rows($result) > 0)
	{
		$response['courses'] = array();
		while($row = mysql_fetch_array($result))
		{
			array_push($response['courses'],$row);
		}
		$response['success'] = 1;
	}
}
}

if(isset($_GET['Title']) )
{
	$Title= $_GET['Title'];
	$query = "";

	$query = "select * from courses where Title = $Title";
	
	

	$result = mysql_query($query) or die(mysql_error());
	if($result)
{
	if(mysql_num_rows($result) > 0)
	{
		$response['courses'] = array();
		while($row = mysql_fetch_array($result))
		{
			array_push($response['courses'],$row);
		}
		$response['success'] = 1;
	}
}
}
if(isset($_GET['Subject']) )
{
	$Subject= $_GET['Subject'];
	$query = "";

	$query = "select * from courses where Subject = $Subject";
	
	

	$result = mysql_query($query) or die(mysql_error());
	if($result)
{
	if(mysql_num_rows($result) > 0)
	{
		$response['courses'] = array();
		while($row = mysql_fetch_array($result))
		{
			array_push($response['courses'],$row);
		}
		$response['success'] = 1;
	}
}
}

if(isset($_GET['Timeslot']) )
{
	$Timeslot= $_GET['Timeslot'];
	$query = "";

	$query = "select * from courses where Timeslot = $Timeslot";
	
	

	$result = mysql_query($query) or die(mysql_error());
	if($result)
{
	if(mysql_num_rows($result) > 0)
	{
		$response['courses'] = array();
		while($row = mysql_fetch_array($result))
		{
			array_push($response['courses'],$row);
		}
		$response['success'] = 1;
	}
}
}

if(isset($_GET['Instructor']) )
{
	$Instructor= $_GET['Instructor'];
	$query = "";

	$query = "select * from courses where Instructor = $Instructor";
	
	

	$result = mysql_query($query) or die(mysql_error());
	if($result)
{
	if(mysql_num_rows($result) > 0)
	{
		$response['courses'] = array();
		while($row = mysql_fetch_array($result))
		{
			array_push($response['courses'],$row);
		}
		$response['success'] = 1;
	}
}
}
echo json_encode($response);
?>