//package withkotlin.nullables;
//
//import kotlin.Unit;
//import withjava.common.Common;
//import withjava.common.JavaRecord;
//import withjava.common.Math;
//
//import java.io.IOException;
//
//public class CommonJava {
//
//
//    void print() {
//        Math.add(1, 2);
//    }
//
//    void print2() {
//        final Common common = new Common();
//        common.test("");
//        common.test("", 1);
//        common.test("", 1, "");
//    }
//
//    void print3() {
//        final Common common = new Common();
//
//        common.lambda((a, b, c) -> {
//                    return Unit.INSTANCE;
//                }
//        );
//    }
//
//    void print4() {
//        final Common common = new Common();
//        try {
//            common.mightThrow();
//        } catch (IOException ignored) {
//
//        }
//    }
//
//    void print5() {
//        final JavaRecord jvmRecord = new JavaRecord("", "", "", 1);
//
//    }
//}
//
