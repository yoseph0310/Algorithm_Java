package SWEA.CodeBattle.실전실습.No_5_개발협업도구;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 *  mTime 은 init() 을 제외하고 매 함수 호출 시 마다 1씩 증가한다.
 */
class UserSolution_Test_Print {

    static final int MAX_CHAR_SIZE = 11;
    static final int MAX_API_CALL = 10_000;
    static final int MAX_FILES_SIZE = 50;

    static HashMap<String, Integer> branchHM;

    static char[] charPool;
    static int charPoolLastIdx;

    static Branch[] branchPool;
    static int branchPoolLastIdx;

    static Queue<File> q1, q2;
    static HashMap<String, File> duplicatedFileName;

    static void createBranch(String name, Branch parent) {
        Branch newBranch = branchPool[branchPoolLastIdx];

        // 새로운 브랜치 정보 기록
        newBranch.isValid = true;
        newBranch.name = name;
        newBranch.rear = 0;
        newBranch.parentBranch = parent;
        while(!newBranch.childBranchQ.isEmpty()) newBranch.childBranchQ.poll();

        branchPool[branchPoolLastIdx] = newBranch;
        branchHM.put(name, branchPoolLastIdx++);
    }

    static class Branch {
        boolean isValid;
        String name;
        File[] fileList;
        int front, rear;

        Branch parentBranch;
        Queue<Branch> childBranchQ;

        public Branch() {
            this.isValid = true;
            this.name = "";
            this.fileList = new File[MAX_FILES_SIZE];
            this.parentBranch = null;
            this.childBranchQ = new LinkedList<>();

            for (int i = 0; i < MAX_FILES_SIZE; i++) {
                this.fileList[i] = new File();
            }
        }

        public File addLast(File addFile) {
            if (rear >= MAX_FILES_SIZE) pollFirst();

            fileList[(front + rear) % MAX_FILES_SIZE] = addFile;
            rear += 1;

            return fileList[(front + rear-1) % MAX_FILES_SIZE];
        }

        public File pollFirst() {
            File file = fileList[front];
            front = (front + 1) % MAX_FILES_SIZE;
            rear -= 1;

            return file;
        }
    }

    static class File {
        int createdTime, editedTime;
        String name, data;

        public File() {}

        public File(int createdTime, int editedTime, String name, String data) {
            this.createdTime = createdTime;
            this.editedTime = editedTime;
            this.name = name;
            this.data = data;
        }
    }
    /**
     * 각 테스트 케이스의 처음에 한번 호출. 호출 직후에는 "root" branch 만 존재하며 아무 파일도 존재하지 않는다.
     */
    void init() {
        branchHM = new HashMap<>();

        branchPool = new Branch[MAX_API_CALL];
        for (int i = 0; i < MAX_API_CALL; i++) {
            branchPool[i] = new Branch();
        }
        branchPoolLastIdx = 0;
        charPoolLastIdx = 0;

        createBranch("root", null);

        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
        duplicatedFileName = new HashMap<>();
    }

    /**
     * mTime 시각, mBranch 가지에 이름이 mFile 인 파일을 생성하고 내용은 mData 이다.
     *
     * 함수 호출 시점에 이름이 mBranch 인 가지가 존재한다.
     * 함수 호출 시점에 mBranch 에 mFile 인 파일은 없다.
     * mBranch, mFile, mData 는 모두 길이가 1 이상 10 이하인 문자열로, 영문 소문자로 이뤄져 있으며 '\0'로 끝난다.
     *
     * @param mTime     : 함수 호출 시각 (1 <= mTime <= 10,000)
     * @param mBranch   : 파일을 생성할 branch 이름
     * @param mFile     : 생성된 파일의 이름
     * @param mData     : 생성된 파일의 내용
     */
    void create(int mTime, char[] mBranch, char[] mFile, char[] mData) {
        String str_mBranch = charToString(mBranch);
        String str_mFile = charToString(mFile);
        String str_mData = charToString(mData);

//        System.out.println("100. create(" + mTime + ", " + str_mBranch + ", " + str_mFile + ", " + str_mData + ")");

        int idx = branchHM.get(str_mBranch);
//        System.out.println("  생성하기 전 파일리스트 상태");
//        System.out.println("    name : " + str_mBranch);
//        for (int i = 0; i < branchPool[idx].fileList.length; i++) {
//            if (branchPool[idx].fileList[(branchPool[idx].front + i) % MAX_FILES_SIZE].name != null) {
//                System.out.println("      file name : " + branchPool[idx].fileList[(branchPool[idx].front + i) % MAX_FILES_SIZE].name);
//            }
//        }
        branchPool[idx].addLast(new File(mTime, mTime, str_mFile, str_mData));

//        System.out.println();
//        System.out.println("  생성한 이후 파일리스트 상태");
//        System.out.println("    name : " + str_mBranch);
//        for (int i = 0; i < branchPool[idx].fileList.length; i++) {
//            if (branchPool[idx].fileList[(branchPool[idx].front + i) % MAX_FILES_SIZE].name != null) {
//                System.out.println("      file (name : " + branchPool[idx].fileList[(branchPool[idx].front + i) % MAX_FILES_SIZE].name + ")," +
//                        " (data : " + branchPool[idx].fileList[(branchPool[idx].front + i) % MAX_FILES_SIZE].data+")");
//            }
//        }

    }

    /**
     * mTime 시각, mBranch 가지의 패일 중 mFile 인 파일을 수정한다. 수정 내용은 mData 이다.
     *
     * 함수 호출 시점에 이름이 mBranch 인 가지가 존재한다.
     * 함수 호출 시점에 mBranch 에 mFile 이 존재한다.
     * mBranch, mFile, mData 는 모두 길이가 1 이상 10 이하인 문자열로, 영문 소문자로 이뤄져 있으며 '\0'로 끝난다.
     *
     * @param mTime     : 함수 호출 시각 (1 <= mTime <= 10,000)
     * @param mBranch   : 수정할 파일이 있는 branch 이름
     * @param mFile     : 수정할 파일의 이름
     * @param mData     : 수정된 파일의 내용
     */
    void edit(int mTime, char[] mBranch, char[] mFile, char[] mData) {
        String str_mBranch = charToString(mBranch);
        String str_mFile = charToString(mFile);
        String str_mData = charToString(mData);

//        System.out.println("200. edit(" + mTime + ", " + str_mBranch + ", " + str_mFile + ", " + str_mData + ")");

        int idx = branchHM.get(str_mBranch);
        Branch branch = branchPool[idx];
        File file = findFile(branch, str_mFile);

//        System.out.println("  찾은 파일의 수정 전 내용");
//        System.out.println("    file name : " + file.name);
//        System.out.println("    file data : " + file.data);

        file.data = str_mData;
        file.editedTime = mTime;

//        System.out.println("  찾은 파일의 수정 후 내용");
//        System.out.println("    file name : " + file.name);
//        System.out.println("    file data : " + file.data);
//
//        System.out.println("  실제 브랜치 풀의 정보에 반영됐는지 확인");
//        Branch b = branchPool[0];
//        for (int i = 0; i < b.fileList.length; i++) {
//            if (b.fileList[i].name.equals(file.name)) {
//                System.out.println("    file name : " + b.fileList[i].name);
//                System.out.println("    file data : " + b.fileList[i].data);
//                break;
//            }
//        }

    }

    // branch 에서 fileName 과 같은 파일을 찾는다.
    File findFile(Branch branch, String fileName) {
        for (int i = 0; i < branch.fileList.length; i++) {
//            System.out.println("  " + branch.fileList[(branch.front + i) % MAX_FILES_SIZE].name);
            File file = branch.fileList[(branch.front + i) % MAX_FILES_SIZE];

            if (file.name.equals(fileName)) {
                return file;
            }
        }

        return null;
    }

    /**
     * mTime 시각에 이름이 mBranch 인 가지를 분기한다.
     * mBranch 로 분기된 가지의 이름은 mChild 이다.
     * 분기 후, mBranch 가지의 파일들을 모두 mChild 가지로 복사한다.
     *
     * 함수 호출 시점에 이름이 mBranch 인 가지가 존재한다.
     * mChild 는 init() 함수 호출 이후 생성된 모든 가지와 이름이 다르다. (생성 후, 삭제된 가지의 이름과도 다르다.)
     * mBranch, mChild 는 모두 길이가 1 이상 10 이하인 문자열로, 영문 소문자로 이뤄져 있으며 '\0'로 끝난다.
     *
     * @param mTime     : 함수 호출 시각 (1 <= mTime <= 10,000)
     * @param mBranch   : 분기할 branch 이름
     * @param mChild    : mBranch 로 부터 분기할 새로 생성된 branch 이름
     */
    void branch(int mTime, char[] mBranch, char[] mChild) {
        String str_mBranch = charToString(mBranch);
        String str_mChild = charToString(mChild);

//        System.out.println("300. branch("+ mTime + ", " +str_mBranch + ", " + str_mChild + ")");

        // 부모 브랜치 인덱스
        int parentIdx = branchHM.get(str_mBranch);
//        System.out.println("  부모 브랜치 정보");
//        System.out.println("    name : " + branchPool[parentIdx].name);

        // mBranch 를 부모로 하는 child 브랜치 생성
        createBranch(str_mChild, branchPool[parentIdx]);

        // 새로 생성된 자식 브랜치 인덱스
        int childIdx = branchHM.get(str_mChild);
//        System.out.println("  새로 생성된 자식 브랜치 정보");
//        System.out.println("    name : " + branchPool[childIdx].name);

        // 부모 브랜치의 자식 브랜치 목록에 생성된 브랜치를 추가.
        branchPool[parentIdx].childBranchQ.add(branchPool[childIdx]);

        // 부모 브랜치의 파일리스트를 자식 브랜치로 모두 복사한다.
        for (int i = 0; i < branchPool[parentIdx].fileList.length; i++) {
            int idx = (branchPool[parentIdx].front + i) % MAX_FILES_SIZE;


            if (branchPool[parentIdx].fileList[idx].name != null) {
                File file = branchPool[parentIdx].fileList[idx];
                File cFile = branchPool[childIdx].addLast(new File(file.createdTime, file.editedTime, file.name, file.data));
//                System.out.println("      file name : " + branchPool[parentIdx].fileList[idx].name);
            }
        }
    }

    /**
     * mTime 시각, 이름이 mBranch 인 가지를 해당 가지의 부모 가지로 병합.
     * mBranch 는 "root" 가 아니며 함수 호출 시점에 이름이 mBranch 인 가지가 존재한다.
     * mBranch 는 길이가 1 이상 10 이하인 문자열로, 영문 소문자로 이뤄져 있으며 '\0'로 끝난다.
     *
     * @param mTime     : 함수 호출 시각 (1 <= mTime <= 10,000)
     * @param mBranch   : 부모 가지로 병합할 가지 이름
     */
    void merge(int mTime, char[] mBranch) {
        String str_mBranch = charToString(mBranch);

//        System.out.println("400. merge(" + mTime + ", " + str_mBranch + ")");
//        printCharArr(mBranch);
        // mBranch 의 부모 브랜치로 병합한다.
        int idx = branchHM.get(str_mBranch);
        merge(branchPool[idx]);
    }

    void merge(Branch branch) {
        Branch parent = branch.parentBranch;
//        System.out.println("  병합할 " + branch.name + " 의 부모 브랜치 : " + parent.name);

        // 만약 병합하려는 브랜치가 자식 브랜치가 있다면 먼저 자기 자식 브랜치들 부터 병합한다.
        while (!branch.childBranchQ.isEmpty()) {
            Branch child = branch.childBranchQ.peek();

            if (child.isValid) merge(child);
            branch.childBranchQ.poll();
        }

        // 파일 중복 해시맵 초기화
        duplicatedFileName.clear();
        q1.clear();
        q2.clear();

        for (int i = 0; i < branch.fileList.length; i++) {
            File cFile = branch.fileList[(branch.front + i) % MAX_FILES_SIZE];
            if (cFile.name == null) continue;

            File duplicatedFile = null;
            int fileIdx = 0;
            for (int j = 0; j < parent.fileList.length; j++) {
                File pFile = parent.fileList[(parent.front + j) % MAX_FILES_SIZE];
                if (pFile.name == null) continue;

                if (cFile.name.equals(pFile.name)) {
                    duplicatedFile = pFile;
                    fileIdx = (parent.front + j) % MAX_FILES_SIZE;
                    break;
                }
            }

            if (duplicatedFile != null) {
                if (cFile.createdTime == duplicatedFile.createdTime
                        && cFile.editedTime > duplicatedFile.editedTime) {
                    parent.fileList[fileIdx] = cFile;
                }
            } else {
                q2.add(cFile);
            }
        }

        for (int i = 0; i < parent.fileList.length; i++) {
            File file = parent.fileList[(parent.front + i) % MAX_FILES_SIZE];
            if (file.name != null) q1.add(file);
        }

        parent.rear = 0;
        while (!q1.isEmpty() || !q2.isEmpty()) {
//            parent.addLast((q2.isEmpty() || !q1.isEmpty() && !q2.isEmpty() && q1.peek().createdTime < q2.peek().createdTime) ? q1.poll() : q2.poll());
            Queue<File> q = (q2.isEmpty() || !q1.isEmpty() && !q2.isEmpty() && q1.peek().createdTime < q2.peek().createdTime) ? q1 : q2;
            parent.addLast(q.poll());
        }

        branch.isValid = false;
    }

    /**
     * mTime 시각, mBranch 가지에 있는 파일 중, 이름이 mFile 인 파일의 내용을 읽는다.
     * 파일 내용은 retString 문자 배열에 담고 내용 길이를 반한다.
     *
     * 함수 호출 시점에 이름이 mBranch 인 가지가 존재한다.
     * 함수 호출 시점에 mBranch 가지에는 mFile 이 존재한다.
     * mBranch, mFile 는 모두 길이가 1 이상 10 이하인 문자열로, 영문 소문자로 이뤄져 있으며 '\0'로 끝난다.
     *
     * @param mTime     : 함수 호출 시각 (1 <= mTime <= 10,000)
     * @param mBranch   : 파일이 위치한 가지의 이름
     * @param mFile     : 파일의 이름
     * @param retString : 반환할 파일 내용을 저장하는 문자열 변수
     *
     * @return          : 읽은 파일 내용을 retString 에 담고 그 길이를 반환한다.
     */
    int readfile(int mTime, char[] mBranch, char[] mFile, char[] retString) {
//        Arrays.fill(retString, '\0');
        String str_mBranch = charToString(mBranch);
        String str_mFile = charToString(mFile);
        String str_retString = charToString(retString);

//        System.out.println("500. readfile(" + mTime + ", " + str_mBranch + ", " + str_mFile + ", " + str_retString + ")");

        int idx = branchHM.get(str_mBranch);
        File file = findFile(branchPool[idx], str_mFile);

        Arrays.fill(retString, '\0');
        for (int i = 0; i < file.data.length(); i++) {
            retString[i] = file.data.charAt(i);
        }

//        for (int i = 0; i < retString.length; i++) {
//            System.out.print(retString[i]);
//        }
//        System.out.println();

        return file.data.length();
    }

    static String charToString(char[] arr) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '\0') break;
            else sb.append(arr[i]);
        }

        return sb.toString();
    }

    static void printCharArr(char[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
        System.out.println();
    }
}