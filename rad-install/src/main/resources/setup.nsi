
;--------------------------------
;Include Modern UI

  !include "MUI2.nsh"

;--------------------------------
;General

  ;Name and file
  Name "RADS"
  OutFile "rads-community-setup.exe"

  ;Default installation folder
  ;InstallDir "$PROGRAMFILES\RADS"
  InstallDir "C:\Rad-Service"

  ;Get installation folder from registry if available
  ;InstallDirRegKey HKCU "Software\RADS"

  ;Request application privileges for Windows Vista
  RequestExecutionLevel user

;--------------------------------
;Variables and Constants

  !define INSTALL_TYPE_FULL full
  !define INSTALL_TYPE_UPGRADE upgrade
  !define INSTALL_TYPE_MINOR_UPGRADE minor_upgrade
  !define INSTALL_TYPE_UPDATE update
  !define INSTALL_TYPE_ABORT abort
  Var INSTALL_TYPE
  Var EXISTING_TOMCAT_VERSION 


;--------------------------------
;Interface Settings

  !define MUI_ABORTWARNING
  !define MUI_HEADERIMAGE
  !define MUI_HEADERIMAGE_BITMAP "rads_logo.bmp"
  !define MUI_HEADERIMAGE_RIGHT
  !define MUI_HEADERIMAGE_BITMAP_NOSTRETCH
  !define MUI_ICON "rads.ico"

;--------------------------------
;Language Selection Dialog Settings

  ;Remember the installer language
  ;!define MUI_LANGDLL_REGISTRY_ROOT "HKCU"
  ;!define MUI_LANGDLL_REGISTRY_KEY "Software\RADS"
  ;!define MUI_LANGDLL_REGISTRY_VALUENAME "RADS Installer Language"
  !define MUI_LANGDLL_ALLLANGUAGES 
;--------------------------------

;Pages

  !insertmacro MUI_PAGE_WELCOME
  !insertmacro MUI_PAGE_LICENSE "LICENSE.txt"
  !insertmacro MUI_PAGE_COMPONENTS
  !insertmacro MUI_PAGE_DIRECTORY
  !insertmacro MUI_PAGE_INSTFILES
  !insertmacro MUI_PAGE_FINISH

  !insertmacro MUI_UNPAGE_WELCOME
  !insertmacro MUI_UNPAGE_CONFIRM
  !insertmacro MUI_UNPAGE_INSTFILES
  !insertmacro MUI_UNPAGE_FINISH

;--------------------------------
;Languages

  !insertmacro MUI_LANGUAGE "English" ;first language is the default language
  !insertmacro MUI_LANGUAGE "French"
  !insertmacro MUI_LANGUAGE "German"
  !insertmacro MUI_LANGUAGE "Spanish"
  !insertmacro MUI_LANGUAGE "SpanishInternational"
  !insertmacro MUI_LANGUAGE "SimpChinese"
  !insertmacro MUI_LANGUAGE "TradChinese"
  !insertmacro MUI_LANGUAGE "Japanese"
  !insertmacro MUI_LANGUAGE "Korean"
  !insertmacro MUI_LANGUAGE "Italian"
  !insertmacro MUI_LANGUAGE "Dutch"
  !insertmacro MUI_LANGUAGE "Danish"
  !insertmacro MUI_LANGUAGE "Swedish"
  !insertmacro MUI_LANGUAGE "Norwegian"
  !insertmacro MUI_LANGUAGE "NorwegianNynorsk"
  !insertmacro MUI_LANGUAGE "Finnish"
  !insertmacro MUI_LANGUAGE "Greek"
  !insertmacro MUI_LANGUAGE "Russian"
  !insertmacro MUI_LANGUAGE "Portuguese"
  !insertmacro MUI_LANGUAGE "PortugueseBR"
  !insertmacro MUI_LANGUAGE "Polish"
  !insertmacro MUI_LANGUAGE "Ukrainian"
  !insertmacro MUI_LANGUAGE "Czech"
  !insertmacro MUI_LANGUAGE "Slovak"
  !insertmacro MUI_LANGUAGE "Croatian"
  !insertmacro MUI_LANGUAGE "Bulgarian"
  !insertmacro MUI_LANGUAGE "Hungarian"
  !insertmacro MUI_LANGUAGE "Thai"
  !insertmacro MUI_LANGUAGE "Romanian"
  !insertmacro MUI_LANGUAGE "Latvian"
  !insertmacro MUI_LANGUAGE "Macedonian"
  !insertmacro MUI_LANGUAGE "Estonian"
  !insertmacro MUI_LANGUAGE "Turkish"
  !insertmacro MUI_LANGUAGE "Lithuanian"
  !insertmacro MUI_LANGUAGE "Slovenian"
  !insertmacro MUI_LANGUAGE "Serbian"
  !insertmacro MUI_LANGUAGE "SerbianLatin"
  !insertmacro MUI_LANGUAGE "Arabic"
  !insertmacro MUI_LANGUAGE "Farsi"
  !insertmacro MUI_LANGUAGE "Hebrew"
  !insertmacro MUI_LANGUAGE "Indonesian"
  !insertmacro MUI_LANGUAGE "Mongolian"
  !insertmacro MUI_LANGUAGE "Luxembourgish"
  !insertmacro MUI_LANGUAGE "Albanian"
  !insertmacro MUI_LANGUAGE "Breton"
  !insertmacro MUI_LANGUAGE "Belarusian"
  !insertmacro MUI_LANGUAGE "Icelandic"
  !insertmacro MUI_LANGUAGE "Malay"
  !insertmacro MUI_LANGUAGE "Bosnian"
  !insertmacro MUI_LANGUAGE "Kurdish"
  !insertmacro MUI_LANGUAGE "Irish"
  !insertmacro MUI_LANGUAGE "Uzbek"
  !insertmacro MUI_LANGUAGE "Galician"
  !insertmacro MUI_LANGUAGE "Afrikaans"
  !insertmacro MUI_LANGUAGE "Catalan"
  !insertmacro MUI_LANGUAGE "Esperanto"

;--------------------------------
;Reserve Files

  ;If you are using solid compression, files that are required before
  ;the actual installation should be stored first in the data block,
  ;because this will make your installer start faster.

  !insertmacro MUI_RESERVEFILE_LANGDLL

;--------------------------------

;Installer Sections

Section "RADS" SecRADS

  SectionIn RO
  SetOutPath "$INSTDIR"

  Call CheckUpgrade

  ${If} $INSTALL_TYPE == "${INSTALL_TYPE_ABORT}"
    MessageBox MB_OK "Existing v2 installation found in the directory '$INSTDIR'.$\r$\nSorry, upgrade from this version is not supported."
    Quit
  ${EndIf}

  ${If} $INSTALL_TYPE != "${INSTALL_TYPE_FULL}"
    ;MessageBox MB_OK $INSTALL_TYPE
    MessageBox MB_YESNO "Existing installation found in the directory '$INSTDIR'.$\r$\nWould you like to update? $\r$\n" IDYES DoUpgrade
    MessageBox MB_OK "Installation aborted"
    Quit

    DoUpgrade:
        ;MessageBox MB_OK "Upgrading"
        RmDir /r "$SMPROGRAMS\RADS Workflow v3"
        RmDir /r "$SMPROGRAMS\RADS Workflow v4"
        RmDir /r "$SMPROGRAMS\RADS Workflow v5"
        RmDir /r "$SMPROGRAMS\RADS Workflow v6"
        RmDir /r "$INSTDIR\apache-tomcat-$EXISTING_TOMCAT_VERSION\webapps\rad"
        RmDir /r "$INSTDIR\apache-tomcat-$EXISTING_TOMCAT_VERSION\webapps\raddesigner"
        CreateDirectory "$INSTDIR\apache-tomcat-$EXISTING_TOMCAT_VERSION\webapps"
        File /oname=apache-tomcat-$EXISTING_TOMCAT_VERSION\webapps\rad.war apache-tomcat-8.5.78\webapps\rad.war
        CreateDirectory "$INSTDIR\data"
        File /oname=data\radsdb-empty.sql data\radsdb-empty.sql
        File /oname=data\radsdb-sample.sql data\radsdb-sample.sql
        File build.xml
        File LICENSE.txt
        File NOTICE.txt
        File VERSION.txt
        File CHANGES.txt
    Return

  ${EndIf}

  ;RADS Files Here
  File /r apache-ant-1.7.1
  CreateDirectory "$INSTDIR\apache-tomcat-8.5.78\webapps"
  File /oname=apache-tomcat-8.5.78\webapps\rad.war apache-tomcat-8.5.78\webapps\rad.war
  CreateDirectory "$INSTDIR\data"
  File /oname=data\radsdb-empty.sql data\radsdb-empty.sql
  File /oname=data\radsdb-sample.sql data\radsdb-sample.sql
  ;File docs
  File /r rads*.*
  File /r _getting_started*.*
  File build.xml
  File LICENSE.txt
  File NOTICE.txt
  File VERSION.txt
  File CHANGES.txt
  ;File README.txt
  File rads.ico
  File rads_start.ico
  File rads_stop.ico

  ;Store installation folder
  ;WriteRegStr HKCU "Software\RADS" "" $INSTDIR

  ;Create uninstaller
  WriteUninstaller "$INSTDIR\Uninstall.exe"

SectionEnd

Section "Apache Tomcat 8" SecTomcat

  SectionIn RO
  SetOutPath "$INSTDIR"

${If} $INSTALL_TYPE == "${INSTALL_TYPE_FULL}"
  ;Tomcat File Here
  File /r /x *.war apache-tomcat-8.5.78
  File tomcat8-run.bat
  File tomcat8-stop.bat
  File rads-start.bat
  File rads-stop.bat

  CreateShortCut "$INSTDIR\Start RADS Server.lnk" "$INSTDIR\rads-start.bat" "Start RADS Server" "$INSTDIR\rads_start.ico"
  CreateShortCut "$INSTDIR\Stop RADS Server.lnk" "$INSTDIR\rads-stop.bat" "Stop RADS Server" "$INSTDIR\rads_stop.ico"
${EndIf}

SectionEnd

Section "Java 11" SecJava

  SectionIn RO
  SetOutPath "$INSTDIR"

  ${If} $INSTALL_TYPE == "${INSTALL_TYPE_FULL}"  
    ;Java Files Here
    File /r jre11.0.13
  ${EndIf}  

SectionEnd

Section "MariaDB 10" SecMariaDB

  SectionIn RO
  SetOutPath "$INSTDIR"

  ${If} $INSTALL_TYPE == "${INSTALL_TYPE_FULL}"
    ;MariaDB Files Here
    File /r mariadb-10.3.9-win32
    File mariadb-start.bat
    File mariadb-stop.bat
  ${EndIf}

SectionEnd

Section "Start Menu Shortcuts" SecStartMenu

  SetOutPath "$INSTDIR"

  CreateDirectory "$SMPROGRAMS\RADS"
  CreateShortCut "$SMPROGRAMS\RADS\Start RADS Server.lnk" "$INSTDIR\rads-start.bat" "Start RADS Server" "$INSTDIR\rads_start.ico"
  CreateShortCut "$SMPROGRAMS\RADS\Stop RADS Server.lnk" "$INSTDIR\rads-stop.bat" "Stop RADS Server" "$INSTDIR\rads_stop.ico"
  CreateShortCut "$SMPROGRAMS\RADS\App Center.lnk" "http://localhost:8080/rad" "App Center" "$INSTDIR\rads.ico"
  CreateShortCut "$SMPROGRAMS\RADS\rads.purwana.net.lnk" "http://rads.purwana.net" "rads.purwana.net" "$INSTDIR\rads.ico"

SectionEnd

;--------------------------------
;Installer Functions

Function .onInit

  
  !insertmacro MUI_LANGDLL_DISPLAY

FunctionEnd


;--------------------------------
;Functions

Function CheckUpgrade

  ${If} ${FileExists} $INSTDIR\apache-tomcat-6.0.18\webapps\rad-designerweb.war
    StrCpy $INSTALL_TYPE ${INSTALL_TYPE_ABORT}
  ${ElseIf} ${FileExists} $INSTDIR\apache-tomcat-8.5.78\webapps\rad.war
    StrCpy $INSTALL_TYPE ${INSTALL_TYPE_UPDATE}
    StrCpy $EXISTING_TOMCAT_VERSION "8.5.78"  
  ${ElseIf} ${FileExists} $INSTDIR\apache-tomcat-8.5.72\webapps\rad.war
    StrCpy $INSTALL_TYPE ${INSTALL_TYPE_UPDATE}
    StrCpy $EXISTING_TOMCAT_VERSION "8.5.72"  
  ${ElseIf} ${FileExists} $INSTDIR\apache-tomcat-8.5.65\webapps\rad.war
    StrCpy $INSTALL_TYPE ${INSTALL_TYPE_UPDATE}
    StrCpy $EXISTING_TOMCAT_VERSION "8.5.65"  
  ${ElseIf} ${FileExists} $INSTDIR\apache-tomcat-8.5.58\webapps\rad.war
    StrCpy $INSTALL_TYPE ${INSTALL_TYPE_UPDATE}
    StrCpy $EXISTING_TOMCAT_VERSION "8.5.58"  
  ${ElseIf} ${FileExists} $INSTDIR\apache-tomcat-8.5.41\webapps\rad.war
    StrCpy $INSTALL_TYPE ${INSTALL_TYPE_UPDATE}
    StrCpy $EXISTING_TOMCAT_VERSION "8.5.41"  
  ${ElseIf} ${FileExists} $INSTDIR\apache-tomcat-8.5.38\webapps\rad.war
    StrCpy $INSTALL_TYPE ${INSTALL_TYPE_UPDATE}
    StrCpy $EXISTING_TOMCAT_VERSION "8.5.38"  
  ${ElseIf} ${FileExists} $INSTDIR\apache-tomcat-8.5.23\webapps\rad.war
    StrCpy $INSTALL_TYPE ${INSTALL_TYPE_UPDATE}
    StrCpy $EXISTING_TOMCAT_VERSION "8.5.23"  
  ${ElseIf} ${FileExists} $INSTDIR\apache-tomcat-8.5.16\webapps\rad.war
    StrCpy $INSTALL_TYPE ${INSTALL_TYPE_UPDATE}
    StrCpy $EXISTING_TOMCAT_VERSION "8.5.16"  
  ${ElseIf} ${FileExists} $INSTDIR\apache-tomcat-8.5.14\webapps\rad.war
    StrCpy $INSTALL_TYPE ${INSTALL_TYPE_UPDATE}
    StrCpy $EXISTING_TOMCAT_VERSION "8.5.14"  
  ${ElseIf} ${FileExists} $INSTDIR\apache-tomcat-8.5.9\webapps\rad.war
    StrCpy $INSTALL_TYPE ${INSTALL_TYPE_UPDATE}
    StrCpy $EXISTING_TOMCAT_VERSION "8.5.9"  
  ${ElseIf} ${FileExists} $INSTDIR\apache-tomcat-8.0.20\webapps\rad.war
    StrCpy $INSTALL_TYPE ${INSTALL_TYPE_UPGRADE}
    StrCpy $EXISTING_TOMCAT_VERSION "8.0.20"
  ${ElseIf} ${FileExists} $INSTDIR\apache-tomcat-7.0.52\webapps\rad.war
    StrCpy $INSTALL_TYPE ${INSTALL_TYPE_UPGRADE}
    StrCpy $EXISTING_TOMCAT_VERSION "7.0.52"
  ${ElseIf} ${FileExists} $INSTDIR\apache-tomcat-7.0.39\webapps\rad.war
    StrCpy $INSTALL_TYPE ${INSTALL_TYPE_UPGRADE}
    StrCpy $EXISTING_TOMCAT_VERSION "7.0.39"
  ${ElseIf} ${FileExists} $INSTDIR\apache-tomcat-6.0.18\webapps\rad.war
    StrCpy $INSTALL_TYPE ${INSTALL_TYPE_UPGRADE}
    StrCpy $EXISTING_TOMCAT_VERSION "6.0.18"
  ${Else}
    StrCpy $INSTALL_TYPE ${INSTALL_TYPE_FULL}
  ${EndIf}
  ;MessageBox MB_OK $INSTALL_TYPE

FunctionEnd


;--------------------------------
;Descriptions

  ;Language strings
  LangString DESC_SecRADS ${LANG_ENGLISH} "Core RADS Application"
  LangString DESC_SecTomcat ${LANG_ENGLISH} "Apache Tomcat Web Application Server"
  LangString DESC_SecJava ${LANG_ENGLISH} "Java 11 Standard Edition"
  LangString DESC_SecMariaDB ${LANG_ENGLISH} "MariaDB 10 Database Server"
  LangString DESC_SecStartMenu ${LANG_ENGLISH} "Start Menu Shortcuts"

  ;Assign language strings to sections
  !insertmacro MUI_FUNCTION_DESCRIPTION_BEGIN
    !insertmacro MUI_DESCRIPTION_TEXT ${SecRADS} $(DESC_SecRADS)
    !insertmacro MUI_DESCRIPTION_TEXT ${SecTomcat} $(DESC_SecTomcat)
    !insertmacro MUI_DESCRIPTION_TEXT ${SecJava} $(DESC_SecJava)
    !insertmacro MUI_DESCRIPTION_TEXT ${SecMariaDB} $(DESC_SecMariaDB)
    !insertmacro MUI_DESCRIPTION_TEXT ${SecStartMenu} $(DESC_SecStartMenu)
  !insertmacro MUI_FUNCTION_DESCRIPTION_END

;--------------------------------
;Uninstaller Section

Section "Uninstall"


  ;Uninstall Files Here
  RMDir /r "$SMPROGRAMS\RADS"

  RmDir /r "$INSTDIR\apache-ant-1.7.1"
  RmDir /r "$INSTDIR\jre11.0.13"
  RmDir /r "$INSTDIR\apache-tomcat-8.5.78\webapps\rad"
  Delete "$INSTDIR\apache-tomcat-8.5.78\webapps\rad.war"
  Delete "$INSTDIR\build.xml"
  Delete "$INSTDIR\LICENSE.txt"
  Delete "$INSTDIR\NOTICE.txt"
  Delete "$INSTDIR\VERSION.txt"
  Delete "$INSTDIR\README.txt"
  Delete "$INSTDIR\CHANGES.txt"
  Delete "$INSTDIR\rads.ico"
  Delete "$INSTDIR\rads_start.ico"
  Delete "$INSTDIR\rads_stop.ico"
  Delete "$INSTDIR\Start RADS Server.lnk"
  Delete "$INSTDIR\Stop RADS Server.lnk"
  Delete "$INSTDIR\tomcat8-run.bat"
  Delete "$INSTDIR\tomcat8-stop.bat"
  Delete "$INSTDIR\mariadb-start.bat"
  Delete "$INSTDIR\mariadb-stop.bat"
  Delete "$INSTDIR\rads-start.bat"
  Delete "$INSTDIR\rads-stop.bat"

  Delete "$INSTDIR\Uninstall.exe"

  ;RMDir "$INSTDIR"

  ;DeleteRegKey /ifempty HKCU "Software\RADS"

  MessageBox MB_OK "Uninstallation complete. Data files in $INSTDIR have not been deleted. $\r$\nPlease delete the folder manually if you do not wish to keep the data."

SectionEnd

;--------------------------------
;Uninstaller Functions

Function un.onInit

  !insertmacro MUI_UNGETLANGUAGE

FunctionEnd
