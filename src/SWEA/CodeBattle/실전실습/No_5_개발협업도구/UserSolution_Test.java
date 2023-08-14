package SWEA.CodeBattle.실전실습.No_5_개발협업도구;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 *  mTime 은 init() 을 제외하고 매 함수 호출 시 마다 1씩 증가한다.
 */
class UserSolution_Test {

    static final int MAX_API_CALL = 10_000;
    static final int MAX_FILES_SIZE = 50;

    // branch 정보를 String key 로 담은 해시맵
    static HashMap<String, Branch> branchHM;

    // Branch 생성을 위한 메모리 풀
    static Branch[] branchPool;
    static int branchPoolLastIdx;

    // merge 를 위한 파일 큐와 중복 파일 확인할 해시맵
    static Queue<File> q1, q2;      // ** 최적화 예상 구간 ** - 환형 큐 직접 구현할 여지가 있음
    static HashMap<String, File> duplicatedFileName;

    // 새 Branch 이름과 부모 Branch 주소가 주어지면 새 Branch 생성하고 새로운 Branch 주소를 반환
    Branch createBranch(char[] branchName, Branch parentBranch) {
        Branch branch = branchPool[branchPoolLastIdx++];
        String str_branchName = charToString(branchName);

        // 새 브랜치 각 변수들 초기화
        branch.isValid = true;
        branch.name = str_branchName;
        branch.filesLength = 0;
        branch.parentBranch = parentBranch;
        while (!branch.childBranches.isEmpty()) branch.childBranches.poll();

        // branchHM 에 새로 생성된 브랜치 추가
        branchHM.put(str_branchName, branch);
        return branch;
    }

    static class Branch {
        boolean isValid;
        String name;
        int filesFirst, filesLength;
        File[] files;

        Branch parentBranch;
        Queue<Branch> childBranches;

        public Branch() {
            this.isValid = true;
            this.name = "";

            // 생성시 모든 요소 null 인 상태
            // addLast 할 때마다 new 연산자를 통해 파일이 생성되고
            // pollFirst 할 때마다 물리적 삭제가 아닌 filesFirst 를 앞당긴다.
            // 즉 인덱스로 관리하면서 덮어 씌우는 식.
            this.files = new File[MAX_FILES_SIZE];
            for (int i = 0; i < MAX_FILES_SIZE; i++) {
                this.files[i] = new File();
            }

            this.parentBranch = null;
            childBranches = new LinkedList<>();
        }

        File pollFirst() {
            File file = files[filesFirst];
            filesFirst = (filesFirst + 1) % MAX_FILES_SIZE;
            filesLength -= 1;

            return file;
        }

        File addLast(File addFile) {
            if (filesLength >= MAX_FILES_SIZE) {
                pollFirst();
            }

            files[(filesFirst + filesLength) % MAX_FILES_SIZE] = addFile;
            filesLength += 1;

            return files[(filesFirst + filesLength) % MAX_FILES_SIZE];
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
        // Branch 생성을 위한 메모리 풀
        branchPool = new Branch[MAX_API_CALL + 1];
        branchPoolLastIdx = 0;

        // branch 정보를 String key 로 담은 해시맵
        branchHM = new HashMap<>();

        for (int i = 0; i <= MAX_API_CALL; i++) {
            branchPool[i] = new Branch();
        }

        char[] root = {'r','o','o','t'};
        createBranch(root, null);

        // merge 를 위한 파일 큐와 중복 파일 확인할 해시맵
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
        String str_branch = charToString(mBranch);
        String str_fileName = charToString(mFile);
        String str_fileData = charToString(mData);

        File file = branchHM.get(str_branch).addLast(new File(mTime, mTime, str_fileName, str_fileData));
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
        System.out.println("200. edit : ");
        for (int i = 0; i < mFile.length; i++) {
            System.out.print(mFile[i]);
        }
        System.out.println();
        System.out.println(charToString(mBranch));
        System.out.println(charToString(mFile));
        File file = findFile(mBranch, mFile);

        // 수정시간 기록과 내용 수정
        file.editedTime = mTime;
        file.data = charToString(mData);
    }

    File findFile(char[] mBranch, char[] mFile) {
        String str_branch = charToString(mBranch);
        String str_fileName = charToString(mFile);
        System.out.println("  findFile : " + str_fileName);

        Branch target = branchHM.get(str_branch);

        for (int i = 0; i < target.files.length; i++) {
            File file = target.files[(target.filesFirst + i) % MAX_FILES_SIZE];
            System.out.println("  target file name : " + file.name);
            if (str_fileName.equals(file.name)) return file;
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
        String parent = charToString(mBranch);
        // 부모 브랜치를 해시맵에서 가져온다.
        Branch parentBranch = branchHM.get(parent);

        // 브랜치 생성 메소드로 새로운 브랜치를 생성한다.
        Branch childBranch = createBranch(mChild, parentBranch);

        // 부모 브랜치의 자식 브랜치 목록에 새로운 브랜치를 추가한다.
        parentBranch.childBranches.add(childBranch);

        // 부모 브랜치의 모든 파일을 자식 브랜치에 복사한다.
        for (int i = 0; i < parentBranch.files.length; i++) {
            int idx = (parentBranch.filesFirst + i) % MAX_FILES_SIZE;
            File file = childBranch.addLast(parentBranch.files[idx]);
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
        // mBranch 의 부모 브랜치로 병합한다.
        merge(branchHM.get(charToString(mBranch)));
    }

    void merge(Branch branch) {
        Branch parentBranch = branch.parentBranch;

        // 만일 병합하려는 브랜치가 자식 노드가 있다면 재귀적으로 병합을 한다.
        while (!branch.childBranches.isEmpty()) {
            Branch child = branch.childBranches.peek();

            // 유효하면 merge 진행
            if (child.isValid) merge(child);
            branch.childBranches.poll();
        }

        // 중복 확인 해시맵 초기화
        duplicatedFileName.clear();

        // 부모 브랜치의 파일들 순회
        for (int i = 0; i < parentBranch.files.length; i++) {
            File file = parentBranch.files[(parentBranch.filesFirst + i) % MAX_FILES_SIZE];

            duplicatedFileName.put(file.name, file);
        }

        // 병합할 브랜치의 파일들 순회
        for (int i = 0; i < branch.files.length; i++) {
            File file = branch.files[(branch.filesFirst + i) % MAX_FILES_SIZE];
            File duplicatedFile = null;

            // 부모 브랜치 파일을 순회하면서 이름이 같은 파일이 있다면 중복된 파일로 저장하고 반복문 종료
            for (int j = 0; j < parentBranch.files.length; j++) {
                File parentFile = parentBranch.files[(parentBranch.filesFirst + j) % MAX_FILES_SIZE];

                if (file.name.equals(parentFile.name)) {
                    duplicatedFile = parentFile;
                    break;
                }
            }

            // 중복된 파일이 있다면
            if (duplicatedFile != null) {
                // 자식 브랜치, 부모 브랜치 동일한 파일을 가지고 있을 경우
                // 생성 시간이 동일하고 자식 브랜치 파일의 수정시간이 더 최신이면 -> 자식 브랜치 반영.
                // 부모 브랜치 파일 업데이트 진행
                if (file.createdTime == duplicatedFile.createdTime) {
                    if (file.editedTime > duplicatedFile.editedTime) {
                        duplicatedFile = file;
                    }
                }
            }
            // 중복된 파일이 없다면
            else {
                q2.add(file);
            }
        }

        for (int i = 0; i < parentBranch.files.length; i++) {
            File file = parentBranch.files[(parentBranch.filesFirst + i) % MAX_FILES_SIZE];
            q1.add(file);
        }

        parentBranch.filesLength = 0;
        while (!q1.isEmpty() || !q2.isEmpty()) {
            Queue<File> q = (q2.isEmpty() || !q1.isEmpty() && !q2.isEmpty() && q1.peek().createdTime < q2.peek().createdTime) ? q1 : q2;
            parentBranch.addLast(q.peek());
            q.poll();
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
        String branch = charToString(mBranch);
        String fileName = charToString(mFile);

        File file = findFile(mBranch, mFile);
        for (int i = 0; i < file.name.length(); i++) {
            retString[i] = file.name.charAt(i);
        }

        return retString.length;
    }

    static String charToString(char[] input) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < input.length; i++) {
            if (input[i] == '\0') break;
            sb.append(input[i]);
        }

        return sb.toString();
    }
}