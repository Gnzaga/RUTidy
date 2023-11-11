
#### If the controller request is successful, it will respond with a message (ResponseConstants.success) and an object

### A task object will have the following ( this can be found by looking at model/task.java )
~~~
 - name of the task
 - description of task
 - Assigned date
 - due date
 - priority
 - status
 - taskID
 - A **list** of assigned user (model/user.java) objects
 - A **single** group (model/group.java) object
~~~

### The data can be defined in the .js file like this:
~~~
const { message, object } = responseData;
const { name, description, assignedDate, dueDate, priority, status, taskID, assignedUsers, group } = object;
~~~
#### Since the users are in an array, you must assign users like this
~~~
const firstAssignedUser = assignedUsers[0];
const assignedUserName = firstAssignedUser.name;
const assignedUserEmail = firstAssignedUser.email;
~~~
#### Alternatively, users can be accesed like this
~~~
const someConstant = assignedUsers[i].desiredField
~~~
**just set i as the user you want to access, and desired field as the field you need**

### If you would liek to display many users you can do the following
~~~
{assignedUsers.map((user, index) =>
    <li key={index}>
    <p> User_field: </p> {user.field}, ....
)}
~~~

**This can be done for each field that is required, and will render the whole list. You can style each as necessary, you could use h1, strong or anything you prefer**

#### Since there is just one group, group fields can be accessed like this 

~~~
    const groupId = group.groupID;
    const groupName = group.name;
    const groupOwner = group.owner;
    const groupOwnerName = groupOwner.name;
    const groupOwnerEmail = groupOwner.email;
~~~