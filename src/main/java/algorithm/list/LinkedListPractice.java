package algorithm.list;

/**
 * 二叉搜索树转双向列表
 * https://www.jianshu.com/p/7e30fb656d63
 */
public class LinkedListPractice {

    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }


        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }
    }


    public static void main(String[] args) {

        ListNode dummyHeader = new ListNode(-1);
        ListNode first = new ListNode(1);
        ListNode second = new ListNode(43);
        ListNode third = new ListNode(3);
        ListNode fourth = new ListNode(9);
        ListNode fifth = new ListNode(5);
        dummyHeader.next = first;
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;

        printList(dummyHeader.next);

        printList(slListSort(dummyHeader.next));

        printList(reverseKList(dummyHeader.next, 2));

        printList(sortList(dummyHeader.next));
    }

    public static void printList(ListNode head) {

        if (head == null || head.next == null) {
            return;
        }
        ListNode cur;
        cur = head;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
        System.out.println("-----------------");
    }

    public static ListNode sortList(ListNode head) {
        /**
         * 只有一个节点返回
         */
        if (head.next == null) {
            return head;
        }

        //快慢指针找出中间结点，这块需要注意一点就是
        //我们需要一个标记sign跟踪慢结点，当找出中间结点时，
        //让中间结点前一结点即sign的下一个结点指向空
        //这样做的目的是为了使前半部分链表和后半部分链表进行合并排序

        //慢节点
        ListNode s = head;
        //快节点
        ListNode f = head;
        //标记节点
        ListNode sign = null;

        while (f!=null&&f.next!= null) {
            sign = s;
            s = s.next;
            f = f.next.next;
        }
        //标记节点下一个节点为空
        sign.next = null;

        ListNode left = sortList(head);

        ListNode right = sortList(s);

        return mergeListV(left, right);
    }

    public static ListNode mergeListV(ListNode l, ListNode r) {

        ListNode dummyHead = new ListNode(0);

        ListNode cur = dummyHead;

        while (l != null && r != null) {
            if (l.val < r.val) {
                cur.next = l;
                l = l.next;
                cur = cur.next;
            } else {
                cur.next = r;
                r = r.next;
                cur = cur.next;
            }
        }
        if (l != null) {
            cur.next = l;
        }
        if (r != null) {
            cur.next = r;
        }
        return dummyHead.next;
    }

    public static ListNode mergeList(ListNode p1, ListNode p2) {
        if (p1 == null) {
            return p2;
        } else if (p2 == null) {
            return p1;
        }
        ListNode newHead;
        if (p1.val < p2.val) {
            newHead = p1;
            newHead.next = mergeList(p1.next, p2);
        } else {
            newHead = p2;
            newHead.next = mergeList(p1, p2.next);
        }

        return newHead;
    }

    /**
     * todo 归并排序
     * https://www.cnblogs.com/du001011/p/10610522.html
     * <p>
     * 链表的选择排序
     * 值交换
     * o(N^2)
     *
     * @param head
     * @return
     */
    public static ListNode slListSort(ListNode head) {

        ListNode p;
        ListNode q;
        //临时变量
        ListNode t;

        if (head == null || head.next == null) {
            return head;
        }

        /**
         * 嵌套循环比较
         */
        for (p = head; p != null; p = p.next) {
            t = p;

            for (q = p.next; q != null; q = q.next) {
                if (q.val < t.val) {
                    t = q;
                }
            }
            if (t != p) {
                int tmpV = t.val;
                t.val = p.val;
                p.val = tmpV;
            }
        }


        return head;
    }

    /**
     * https://mp.weixin.qq.com/s/LVVpFCSI-FByY-1UnD3RNA
     * https://pic.leetcode-cn.com/866b404c6b0b52fa02385e301ee907fc015742c3766c80c02e24ef3a8613e5ad-k%E4%B8%AA%E4%B8%80%E7%BB%84%E7%BF%BB%E8%BD%AC%E9%93%BE%E8%A1%A8.png
     * <p>
     * todo
     * https://www.cnblogs.com/plokmju/p/linkedreverse_toutiao.html
     *
     * @param head
     * @param k
     * @return
     */
    public static ListNode reverseKList(ListNode head, int k) {

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        /**
         * 定义 prev 和 end 节点
         * prev节点指向子链表头部
         */
        ListNode prev = dummy;
        ListNode end = dummy;

        while (end.next != null) {

            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            //不足k不处理
            if (end == null) {
                break;
            }
            //处理子链表
            ListNode start = prev.next;
            /**
             * 下一个开始节点
             */
            ListNode next = end.next;

            end.next = null;
            /**
             * 翻转子链表
             */
            prev.next = reverseList(start);

            //将子链表串起来
            start.next = next;

            /**
             * 移动 prev 和 end 指针
             * start.next = next && end.next 达到移动的目标
             */
            prev = start;
            end = prev;
        }

        return dummy.next;
    }

    public static ListNode reverseList_0(ListNode head) {
        if (head == null || head.next == null)
            return head;
        /**
         * cur 表示移动的指针
         * newHead表示新链表头指针
         */
        ListNode cur, newHead = null;
        /**
         * 刚开始头结点就是当前指针
         */
        cur = head;

        while (cur != null) {

            ListNode tmp = cur.next;//暂存当前结点下一个结点
            /**
             * 当前结点下一个结点指向newHead
             */
            cur.next = newHead;

            /**
             * 新链表的头移动到cur，扩长一步链表
             */
            newHead = cur;

            /**
             * 移动cur指针到下一个结点
             */
            cur = tmp;
        }

        return newHead;
    }

    public static ListNode reverseList_V(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev, cur, end;

        prev = null;

        cur = head;

        end = head.next;

        while (cur != null) {

            /**
             * 当前结点下一个结点指向newHead
             */
            cur.next = prev;//第一个节点指向null

            /**
             * 新链表的头移动到cur，扩长一步链表
             */
            prev = cur;

            /**
             * 移动cur指针到下一个结点
             */
            cur = end;

            if (end != null) {
                end = end.next;
            }
        }

        return prev;
    }


    public static ListNode reverseList(ListNode head) {
        //递归
        //1.第一个条件是判断递归开始，传入的参数的合法性。第二个是递归的终止条件
        if (head == null || head.next == null) {
            return head;
        }
        //2.开始进行递归
        ListNode newHead = reverseList(head.next);
        //3.尾部4-5-null中，head=4,head.next=4-5 head.next.next=4-5-null,也就是5的后继指向4
        head.next.next = head;
        //4.断开之前4-5之间的连接，将4的后继指向null
        head.next = null;
        //5.返回已经反转的链表
        return newHead;
    }


}
