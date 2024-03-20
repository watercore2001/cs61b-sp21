# BSTMap MY implementation VS Built-in TreeMap

## Recursion

~~f(N) = f(N/2) + f(N/2) + O(N) = N + N + ... + N (log2N个) = O(Nlog2N)~~
Binary Search Tree 天生地可以进行递归
设 N 为 string 数量， M 为 string 长度，分析 get() 运算的计算复杂度

树平衡的 case (put in random)，计算复杂度最好，为
f(N) = f(N/2)  + O(M) = M + M + M + M + ... + M (log2N个) = O(M logN) 

树最不平衡的 case (put in order)，计算复杂度最差，为
f(N) = f(N-1) + f(1) + O(M) = O(MN)

## size

维护一个 size 变量
问题:并不是所有的 put() 和 remove() 都会改变 size
解决方案:将AddEntry从put()中抽象出来，将DelEntry从remove()中抽象出来. size++在AddEntry()和DelEntry中
当然, 不维护size变量, 访问时再递归求解也很优雅.

## put()
TreeMap 如何保证树平衡？
红黑树

## get()
如何区分 value==null 和 key 不存在的情况？
Solution: 这两者的本质区别 
- when value==null，node exists
- when key is not existed, node is also not existed
因此，让getHelper() 返回 Node

## iterator()
定义一个类 BSTIterator, Implement Iterator<Key> 接口, 实现 hasNext
如何遍历？
method1: 递归实现 keySet()，遍历 keySet() 使用 put
method2: 

