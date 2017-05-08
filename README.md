# Android Data Binding

Android Studio 2.3.1

buildTools  : '25.0.2',

Gradle 3.4.1
 'com.android.tools.build:gradle:2.3.1'

Operating system:
macOS Sierra version 10.12.4

-----

In module *app*, I have the reorder_card_activity.xml  with this code:
 <LinearLayout
            ......>
            <include layout="@layout/toolbar_default" android:id="@+id/toolbar_binding"/>


You can see, that I am including a toolbar (toolbar_default.xml), which is in the module *core* and has this code:  
<?xml version="1.0" encoding="utf-8"?>
<layout ....>
<android.support.v7.widget.Toolbar android:id="@+id/toolbar"
  ....>

<de.payback.core.ui.widget.PaybackFontTextView
        android:id="@+id/toolbar_title" ...>


---------------------
Inside the ReorderCardActivity.java from the module *app*, which has the reorder_card_activity.xml set as contentView, I want to do the following:

binding.toolbarBinding.toolbarTitle

binding.toolbarBinding.toolbar


1) This is not working, toolbarTitle or toolbar is not found. (There is also no ToolbarDefaultBinding.java inside the module *app*, only in *core*)
2) If I am am copying toolbar_default.xml from *core* to the module *app*, everything is alright and this is working. (The generated ToolbarDefaultBinding.java is inside the module app and inside the module *core*)