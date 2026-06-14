# Using `PreparedStatement`

`PreparedStatement` allows you to execute parameterized SQL queries safely and efficiently.

## Step 1: Create a Database Connection

Create a connection to the database:

```java
Connection myCon = DriverManager.getConnection(url, username, password);
```

---

## Step 2: Prepare the SQL Statement

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

---

## Step 3: Set Parameter Values

Each placeholder corresponds to an index starting at **1**:

```java
myStmt.setInt(1, 10);
myStmt.setString(2, "Chhavi");
```

---

## Step 4: Execute the Query

### For `SELECT` statements

```java
ResultSet myRs = myStmt.executeQuery();
```

### For `INSERT`, `UPDATE`, and `DELETE` statements

```java
int rowsAffected = myStmt.executeUpdate();
```

`rowsAffected` contains the number of rows modified by the query.

---

## Step 5: Process the Results (`SELECT` only)

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

---

## Step 6: Close Resources

Always close database resources when they are no longer needed:

```java
myRs.close();
myStmt.close();
myCon.close();
```

Alternatively, a `try-with-resources` block can be used to close them automatically.

## Summary

| SQL Statement | Execution Method  |
| ------------- | ----------------- |
| `SELECT`      | `executeQuery()`  |
| `INSERT`      | `executeUpdate()` |
| `UPDATE`      | `executeUpdate()` |
| `DELETE`      | `executeUpdate()` |

## General Workflow

```text
Connection
    ↓
PreparedStatement
    ↓
Set parameters
    ↓
Execute query
    ↓
Process ResultSet (SELECT only)
    ↓
Close resources
```


### Source

``https://www.geeksforgeeks.org/java/how-to-use-preparedstatement-in-java/``