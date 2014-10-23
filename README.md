InstalledApplications
=====================

Display the list of applications installed in the system and categorize them

Here is a simple android application which covers all of the android application components (activity, services, broadcast receiver and content provides).
 
Application monitor
------------------------
1. The application will be used to monitor the type of permissions used by the application installed on the device.
2. On first launch, the application will scan all the installed application, and group them under various category:
    • Costs money (Sends sms, make call, etc.)
    • Access private data (access contacts, reads inbox, track location, etc.)
    • Normal (lower risk permissions: Uses camera, Bluetooth, etc.)
3. UI to display various applications installed on the device under above mentioned category list. Display them in tab or list activity.
4. On selecting a particular application, it should display all the permissions used by the application and should also provide an option to uninstall that application .
5. When a new application is installed on the device, it would prompt with a system notification. Provide an alert dialog, if application uses high risk permission.
6. The application should auto start on boot.
