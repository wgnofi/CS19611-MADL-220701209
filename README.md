# CS19611-MADL-220701209
List of experiments conducted for MAD lab @Rec

## Key takeaways:

### SD Card:
```
val file = File(getExternalFilesDir(null), "emp.txt")
```
*File()* takes two arguments - (directory, file name)

*getExternalFilesDir(null)* gets the external file directory (not involving internal app storage) typically SD Card, null here says it gets the root, specifying a name will create a directory under the root.

```
val writer = FileWriter(file) // a writer object is created ( internal buffer handling)
writer.close() // the buffer is flushed along when the writer is closed
val reader = BufferedReader(FileReader(file)) // external buffer implementation because it reduces I/O calls.
```

reader takes advantage of BufferedReader because it provides a temporary area for faster reads (reducing frequent calls on disk), and allows extra operations like readline() 

### Telephony:

```
private val PERMISSION_REQUEST_CODE = 101
```
Why a request code? The callback method onRequestPermissionRequest() should know which one to permit, a developer can code to ask for different permissions within the app.

```
val telephonyManager = getSystemService(TELEPHONY_SERVICE) as TelephonyManager
```
getSystemService(service) returns Any? object which needs to be typecasted (in this case TelephonyManager) to access its specific methods.

```
if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED)
```
this whole code checks if the permission for reading phone state is granted if not it asks for permission in a dialog box.

ActivityCompat is a class providing backward compatability for activity-related functionalities

checkSelfPermission returns either PackageManager.PERMISSION_GRANTED or PackageManager.PERMISSION_GRANTED

```
ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_PHONE_STATE), PERMISSION_REQUEST_CODE)
```

this - context

arrayOf(...) - array of permissions to be asked (here only one which is to read the phone state)

Important distinction: we use same request code for all the permissions in the array because it will ask to either allow or deny all those permissions at once.
