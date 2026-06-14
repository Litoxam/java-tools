# Using `PreparedStatement`

`PreparedStatement` allows you to execute parameterized SQL queries safely and efficiently.

## Step 1: Create a Database Connection

Create a connection to the database:

```java
Connection myCon = DriverManager.getConnection(url, username, password);
```

### Variable Name

* `myCon` = **my Connection**

---

## Step 2: Build and Prepare the SQL Statement

Instead of hardcoding values directly into the query:

```sql
SELECT * FROM students WHERE age > 10 AND name = 'Chhavi';
```

use parameter placeholders (`?`):

```sql
SELECT * FROM students WHERE age > ? AND name = ?;
```

Create the `PreparedStatement`:

```java
PreparedStatement myStmt =
    myCon.prepareStatement(
        "SELECT * FROM students WHERE age > ? AND name = ?"
    );
```

### Variable Name

* `myStmt` = **my Statement**

### Building the Query

1. Write the SQL query using `?` placeholders.
2. Create the `PreparedStatement` from the connection.
3. Assign values to each placeholder using the appropriate method:

    * `setString()`
    * `setInt()`
    * `setDouble()`
    * `setBoolean()`
    * etc.

Each placeholder has an index starting from **1**.

Example:

```java
myStmt.setInt(1, 10);
myStmt.setString(2, "Chhavi");
```

---

## Step 3: Execute the Statement

### For `SELECT` statements

```java
ResultSet myRs = myStmt.executeQuery();
```

### For `INSERT`, `UPDATE`, and `DELETE` statements

```java
int rowsAffected = myStmt.executeUpdate();
```

example :
```java
// Build the query
String query =
    "UPDATE students " +
    "SET age = ? " +
    "WHERE name = ?";

// Create the PreparedStatement
PreparedStatement myStmt = myCon.prepareStatement(query);

// Set the values for each placeholder
myStmt.setInt(1, 26);
myStmt.setString(2, "Bob");

// Execute the query
int rowsAffected = myStmt.executeUpdate();

// Check the result
if (rowsAffected > 0) {
    System.out.println("Student updated successfully.");
} else {
    System.out.println("Student not found.");
}
```

`rowsAffected` contains the number of rows modified by the query.

### Variable Name

* `myRs` = **my ResultSet**

---

## Step 4: Process the Results (`SELECT` only)

Retrieve the data stored in the `ResultSet`:

```java
while (myRs.next()) {

    int id = myRs.getInt("id");
    String name = myRs.getString("name");
    int age = myRs.getInt("age");

    // Process the retrieved data

}
```

`next()` moves the cursor to the next row and returns `false` when no more rows are available.

To retrieve column values, use the method corresponding to the data type:

* `getString()`
* `getInt()`
* `getDouble()`
* `getBoolean()`
* etc.

---

## Step 5: Close Resources

Always close database resources when they are no longer needed:

```java
myRs.close();
myStmt.close();
myCon.close();
```

Alternatively, a `try-with-resources` block can be used to close them automatically.

---

## Summary

| SQL Statement | Execution Method  |
| :------------ | :---------------- |
| `SELECT`      | `executeQuery()`  |
| `INSERT`      | `executeUpdate()` |
| `UPDATE`      | `executeUpdate()` |
| `DELETE`      | `executeUpdate()` |

---

## General Workflow

```text
Connection
    ↓
PreparedStatement
    ↓
Build query with ? placeholders
    ↓
Set parameters with setXXX()
    ↓
Execute statement
    ↓
Get ResultSet (SELECT only)
    ↓
Read rows with next()
    ↓
Retrieve values with getXXX()
    ↓
Close resources
```

## Variable Naming Convention

| Variable | Meaning       |
| :------- | :------------ |
| `myCon`  | my Connection |
| `myStmt` | my Statement  |
| `myRs`   | my ResultSet  |

```
Connection → PreparedStatement → setXXX() → executeQuery()/executeUpdate() → next() → getXXX() → close()
```
