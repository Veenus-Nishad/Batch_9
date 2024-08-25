package com.example.contactsappwithroomdatabaseversion1.notes

/*
 To be able to use ROOM database
    1. Define Table Or Make Entity
    2. Define Your Dao
    3. Build the Data Base
    4. Reflect in UI

Step 1 => How to make Table or Entity
    1. Make a Data Class with parameter Similar to your dataBase
    2. Annotate the data class with " @Entity " which will Automatically Generate Table with col name as your para names
        -> to make primary key use annotation " @PrimaryKey(autoGenerate=true) " to autogenerate an id

Step 2 => How to Define Dao or Data access Object
    1. make an Interface and Annotate with " @Dao "as we use interface so that functionality can be added later
        on we declare functions and Functionality is added by Room Automatically
    2. Declare Functionality and Annotate with Appropriate Annotation , Such as " @Delete " for the Delete Functionality

Step 3 => Building Database
    1.create a abstract class and inherit it from " RoomDatabase() " and Annotate with " @Database " and fill the
        necessary fields
    2.we make it abstract to dodge the implement members error
    3. implement an abstract function of type of your Dao which will help you access your Dao
        " abstract fun dao():ContactDao "
    4. Now Create DataBase Object as we want only one Object and that Object should write on the database from wherever
        it is called from we will make a singleton Object

Step 4 => Reflect on UI
    1. create a db object
*/