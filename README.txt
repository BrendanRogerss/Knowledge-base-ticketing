Setup Instructions:
1. Compile all .java files in classes/~ directory.
2. Setup database using "Database Setup" instructions
3. Run Tomcat server

------------------------------------------------------------------------------------------------------------------------

Database Setup:

	Database uses university provided MySQL database
	Putty:
		hostname: jumpgate.newcastle.edu.au with port 22
		source port: 30001
		destination: teachdb:3306
		session: SENG2050

		username: c3234953
		password: 150896
		databse name: c3234953_db

	SQL file is located in FinalProject folder as "SENG2050_A3_SQLScript.sql"

------------------------------------------------------------------------------------------------------------------------

User Login Data:

	All passwords: pass

	username: staff1		username: student1
	type: staff				type: student

	username: staff2		username: student2
	type: staff				type: student

	username: staff3		username: student3
	type: staff				type: student

------------------------------------------------------------------------------------------------------------------------

Assumptions:

1. All users already have accounts in the system
2. Staff can see all issues, users can only see their own reported issues.
3. ONLY 'Resolved' issues can be added to Knowledge Base.
4. When a staff comments or proposes a solution on an issue, the associated user receives a notification.
5. Staff can propose a solution whilst status is marked as 'Waiting on reporter'.
6. No one can add comments to a Knowledge Base article.
7. All users can view all Knowledge Base articles.
8. Only staff can propose solutions on issues.

------------------------------------------------------------------------------------------------------------------------

Additional Requirements:
9. “For a Knowledge-Base to work we need to be able to capture the data relating to an incident in a more
    meaningful manner. Currently we capture this data in a free-form textbox (Appendix 6.1). The suggested
    text is related to the category of incident but can be cleared or ignored by users” – IT staff.
    (weight 20)
10. “The categories are very broad and could congest the Knowledge-Base. Can we have some sub-categories
    as well (Appendix 6.2)” – IT staff.
    (weight 20)
14. “We should be able to view Knowledge-Base articles sorted by their categories” – User.
    (weight 5)
15. “We should be able to sort the issues by the date that they were reported” – IT staff.
    (weight 5)
17. “Not all incidents will make it to the Knowledge-Base right away. We need a useful way to search over
    the old incidents that aren’t in the Knowledge-Base” – IT staff.
    (weight 20)
22. “It would be nice if I could view the entire system on my mobile device as well” – User
    (total weight * 2 + 5)

    TOTAL POTENTIAL ADDITIONAL MARKS: 145

------------------------------------------------------------------------------------------------------------------------