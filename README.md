A forum using Spring MVC to create

function: 
1. base membership create and login
2. upload multiple file to relational database
3. all post, file and comment saved in database
4. different user role permissions:
     4.1. normal usser: read/write post
     4.2 admin: read/write post, manage user account/post

Datebase name:	jdbc:derby://localhost:1527/s380f_db
username:	nbuser
password:	nbuser

Registered user account:(username / password):
1.	kenny	kennypw123
2.	benChan	benChanpw
3.	LilyTse	LilyTsepw
4.	user	userpw

Administrator account(username / password):
1.	keith	keithpw
2.	admin	adminpw
3.	andrew	andrewpw

The list of additional features implemented and any necessary details for running them successfully:
Additional Features1: History on the existing and previous forum poll results
Additional Features2: Batch uploading of file attachments
Additional Features3: Storing file attachments to the relational database

file attachments size limite:
max file size: 		20971520
max request size:	41943040
file size threshold:	5242880


SQL file: Other Source/default package/create_db.sql

screenshot:

<B>Index/Forum page: </B></br>
<img src="https://github.com/KennyLTW/forum/blob/master/screenshot/1.PNG" width="800"/> </br>
<img src="https://github.com/KennyLTW/forum/blob/master/screenshot/2.PNG" width="800"/> </br>
<img src="https://github.com/KennyLTW/forum/blob/master/screenshot/3.PNG" width="800"/> </br>
<img src="https://github.com/KennyLTW/forum/blob/master/screenshot/4.PNG" width="800"/> </br>


<B>User management page: </B></br>
<img src="https://github.com/KennyLTW/forum/blob/master/screenshot/5.PNG" width="800"/> </br>
<img src="https://github.com/KennyLTW/forum/blob/master/screenshot/6.PNG" width="800"/> </br>
