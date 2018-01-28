package tree.traversal

import sun.reflect.generics.tree.Tree
import tree.adt.{HuffmanEncodingTree, Leaf}

case class TreeTraversal[A](tree: Tree[A]) {
  def preorder(): List[A] = {
    def go(tree: HuffmanEncodingTree[A]): List[A] = {
      List.empty
    }

    List.empty
  }

  def inorder(): List[A] = {
    List.empty
  }

  def postorder(): List[A] = {
    List.empty
  }

  def leveling(): List[A] = {

  }

}
