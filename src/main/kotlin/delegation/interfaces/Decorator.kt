package delegation.interfaces

import java.io.BufferedInputStream
import java.io.FileInputStream
import java.io.InputStream
import java.io.ObjectInputStream
import java.util.zip.ZipInputStream


val fis: InputStream = FileInputStream("")
val bis: InputStream = BufferedInputStream(fis)
val gis: InputStream = ZipInputStream(bis)
val ois = ObjectInputStream(gis)
