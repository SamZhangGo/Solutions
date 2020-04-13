package samzhanggo.leetcode.practice.medium.threesum;

import java.util.*;

/**
 * @author: siming.zhang
 * 2020/4/10 15:14
 */
public class BucketMapSolution implements ThreeSumSolution {
    private static final int MAX_BUCKET_INDEX = 9;
    private static final int MIN_BUCKET_INDEX = -9;
    private Map<String, List<Integer>> bucketMap;
    private Map<String, Boolean> bucketCheckedMap;
    private Map<Integer, Integer> numMap;
    private Map<String, Boolean> tupleHitMap;
    private List<List<Integer>> retList;
    private int zeroCount = 0;
    private boolean hasZero = false;
    private boolean hasNegativeBucket = false;
    private boolean hasPositiveBucket = false;


    @Override
    public List<List<Integer>> threeSum(int[] nums) {
        zeroCount = 0;
        tupleHitMap = new HashMap<>();
        bucketMap = new HashMap<>();
        bucketCheckedMap = new HashMap<>();
        numMap = new HashMap<>();
        retList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                hasZero = true;
                break;
            }
        }
        classifyToBucket(nums);
        if (zeroCount >= 3) {
            addTuple(0, 0, 0);
        }
//        checkOppositeBucket();
        checkFromOneSideBucket(true);
        checkFromOneSideBucket(false);
        if (retList.size() > 0) {
            retList.sort((o1, o2) -> {
                int ret = o1.get(0) - o2.get(0);
                if (ret == 0) {
                    int ret2 = o1.get(1) - o2.get(1);
                    if (ret2 == 0) {
                        return o1.get(2) - o2.get(2);
                    }
                    return ret2;
                }
                return ret;
            });
        }
        return retList;
    }

    private void checkFromOneSideBucket(boolean isNegative) {
        boolean hasBucket = isNegative ? hasNegativeBucket : hasPositiveBucket;
        int startBucketIndex = isNegative ? MIN_BUCKET_INDEX : MAX_BUCKET_INDEX;
        if (!hasBucket) {
            return;
        }
        for (int i = startBucketIndex; ; ) {
            if (containsBucket(i, isNegative)) {
                int j = MAX_BUCKET_INDEX;
                if (!isNegative) {
                    j = MIN_BUCKET_INDEX;
                }
                for (; ; ) {
                    int bucketAIndex = 0;
                    int bucketBIndex = 0;
                    if (isNegative) {
                        if (-i >= j) {
                            bucketAIndex = j;
                            bucketBIndex = -i - j;
                        } else {
                            bucketAIndex = j;
                            bucketBIndex = ((-i + 10) - j) % 10;
                        }
                    } else {
                        if (-i <= j) {
                            bucketAIndex = j;
                            bucketBIndex = -i - j;
                        } else {
                            bucketAIndex = j;
                            bucketBIndex = ((-i - 10) - j) % 10;
                        }
                    }
                    plusTwoBucket(bucketAIndex, bucketBIndex, isNegative);
                    if (j == (-i) >> 1) {
                        break;
                    }
                    if (j == 0) {
                        break;
                    }
                    if (isNegative) {
                        j--;
                    } else {
                        j++;
                    }

                }
            }
            if (i == 0) {
                break;
            }
            if (isNegative) {
                i++;
            } else {
                i--;
            }
        }
    }

    private void plusTwoBucket(int bucketAIndex, int bucketBIndex, boolean isNegative) {
        if (bucketCheckedMap.containsKey(getBucketIndex(bucketAIndex, isNegative) + "|" + getBucketIndex(bucketBIndex, isNegative))) {
            return;
        }
        if (bucketAIndex != bucketBIndex) {
            List<Integer> complementAList = getBucket(bucketAIndex, !isNegative);
            List<Integer> complementBList = getBucket(bucketBIndex, !isNegative);
            if (complementAList != null && complementBList != null) {
                for (int n = 0; n < complementAList.size(); n++) {
                    int a = complementAList.get(n);
                    for (int m = 0; m < complementBList.size(); m++) {
                        int b = complementBList.get(m);
                        int c = -a - b;
                        if (numMap.containsKey(c)) {
                            addTuple(c, a, b);
                        }
                    }
                }
            }
        } else {
            List<Integer> complementAList = getBucket(bucketAIndex, !isNegative);
            if (complementAList != null) {
                for (int n = 0; n < complementAList.size(); n++) {
                    int a = complementAList.get(n);
                    int count = numMap.get(a);
                    for (int m = 0; m < complementAList.size(); m++) {
                        int b = complementAList.get(m);
                        if (a == b) {
                            if (count > 1) {
                                int c = -a * 2;
                                if (numMap.containsKey(c)) {
                                    addTuple(c, a, a);
                                }
                            }
                        } else {
                            int c = -a - b;
                            if (numMap.containsKey(c)) {
                                addTuple(c, a, b);
                            }
                        }
                    }
                }
            }
        }
        bucketCheckedMap.put(getBucketIndex(bucketAIndex, isNegative) + "|" + getBucketIndex(bucketBIndex, isNegative), true);
    }

    private void checkOppositeBucket() {

        if (zeroCount == 0) {
            return;
        }

        for (int i = MIN_BUCKET_INDEX; i <= 0; i++) {
            List<Integer> members = getBucket(i, true);
            if (members == null) {
                continue;
            }
            if (containsBucket(-i, false)) {
                for (int j = 0; j < members.size(); j++) {
                    int a = members.get(j);
                    if (numMap.containsKey(-a)) {
                        addTuple(a, 0, -a);
                    }
                }
            }
        }

    }

    private void classifyToBucket(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            String intStr = String.valueOf(nums[i]);
            Integer leastNum = Integer.parseInt(intStr.substring(intStr.length() - 1));
            String bucketIndex = String.valueOf(leastNum);
            if (leastNum == 0) {
                if (nums[i] > 0) {
                    bucketIndex = "+0";
                } else if (nums[i] < 0) {
                    bucketIndex = "-0";
                }
            } else {
                if (nums[i] > 0) {
                    bucketIndex = "+" + leastNum;
                } else if (nums[i] < 0) {
                    bucketIndex = String.valueOf(-leastNum);
                }
            }
            if (nums[i] > 0) {
                addNumToBucket(nums[i], bucketIndex);
                hasPositiveBucket = true;
            } else if (nums[i] < 0) {
                addNumToBucket(nums[i], bucketIndex);
                hasNegativeBucket = true;
            } else {
                zeroCount++;
            }
        }
    }

    private void addNumToBucket(int num, String bucketIndex) {
        int count = 0;
        if (numMap.containsKey(num)) {
            count = numMap.compute(num, (k, v) -> v + 1);
        } else {
            numMap.put(num, 1);
            count = 1;
        }
        if (count == 1) {
            if (hasZero && numMap.containsKey(-num)) {
                addTuple(num, 0, -num, false);
            }
            List<Integer> bucketMemberList = bucketMap.get(bucketIndex);
            if (bucketMemberList == null) {
                bucketMemberList = new ArrayList<>();
                bucketMap.put(bucketIndex, bucketMemberList);
            }
            bucketMemberList.add(num);
        }

    }

    private void addTuple(int i, int j, int k, boolean flag) {
        List<Integer> tuple = new ArrayList<>();
        tuple.add(i);
        tuple.add(j);
        tuple.add(k);
        tuple.sort((o1, o2) -> o1 - o2);
        if (flag) {
            if (!tupleHitMap.containsKey(tuple.get(0) + "|" + tuple.get(1) + "|" + tuple.get(2))) {
                tupleHitMap.put(tuple.get(0) + "|" + tuple.get(1) + "|" + tuple.get(2), true);
                retList.add(tuple);
            }
        } else {
            retList.add(tuple);
        }

    }

    private void addTuple(int i, int j, int k) {
        addTuple(i, j, k, true);
    }


    private boolean containsBucket(int index, boolean isNegative) {
        String bucketIndex = getBucketIndex(index, isNegative);
        return bucketMap.containsKey(bucketIndex);
    }

    private List<Integer> getBucket(int index, boolean isNegative) {
        String bucketIndex = getBucketIndex(index, isNegative);
        List<Integer> members = bucketMap.get(bucketIndex);
        return members;
    }

    private String getBucketIndex(Integer index, boolean isNegative) {
        if (index > 0) {
            return "+" + index;
        } else if (index < 0) {
            return index.toString();
        } else {
            if (isNegative) {
                return "-" + index;
            } else {
                return "+" + index;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(String.valueOf(-1122).charAt(String.valueOf(-1122).length() - 1));
        System.out.println(String.valueOf(342234));
        Map<Integer, Integer> numMap = new HashMap<>();
        System.out.println(-9 >> 1);
//        numMap.putif;
//        numMap.compute(1, (k, v) -> v + 1);

        System.out.println(String.valueOf(-0));
    }
}
