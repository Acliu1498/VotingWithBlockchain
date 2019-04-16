*******************
Resouces
*******************

.. contents:: Table of Contents

Elections
===================
Data
-------------------
- id: Integer
   - The unique identifier of the election.
   - This value is auto-generated.
- name: String
   - This is the given name of the election.
- startDateTime: LocalDateTime
   - This is the day and time the election starts.
- endDateTime: LocalDateTime
   - This is the day and time the election ends.
- finished: Boolean = false
   - This states if the election is finished.
   - It is set to false by default.

Notes
-------------
- The LocalDateTime object follows the `ISO_LOCAL_DATE_TIME <https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html#ISO_LOCAL_DATE_TIME/>`_. format
- An election is only marked as finished once postResults have been called

Candidates
===================
Data
-------------------
- id: Integer
    - The unique identifier for the candidate
    - this value is not auto-generated
- email: String
    - The email of the candidate
- lastName: String,
    - The candidate's last name
- firstName: String,
    - The Candidate's first name
- resume: String = "",
    - The location of the resume for the candidate if provided
    - This value is empty by default
- image: String = ""
    - The location of the image for the candidate if provided
    - This value is empty by default

Voters
===================
- voterId: Integer
   - This is an identifier for the voter
- hasVoted: Boolean = false,
   - This keeps track of whether or not the voter has voted in the associated election
- election: Election

