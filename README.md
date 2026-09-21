After pulling the project:

1. Open it in **NetBeans 8.2 using Java 8**. If NetBeans shows missing server/library references, select your own **GlassFish** installation.

2. Go to **Services → Databases → Java DB → Start Server**.

3. Right-click **Java DB → Create Database**, then enter:
   - Database: `employee`
   - Username: `app`
   - Password: `app`

4. Connect to that database, right-click its connection → **Execute Command**, and run:

```sql
CREATE TABLE APP.STUDENT (
    ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1) PRIMARY KEY,
    NAME VARCHAR(100) NOT NULL,
    EMAIL VARCHAR(100) NOT NULL,
    COURSE VARCHAR(100) NOT NULL,
    PHONE VARCHAR(20)
);
```

5. Right-click the project → **Clean and Build**, then **Run**.

Keep Java DB running on port **1527**. The app starts with an empty list—you can add your own records.
