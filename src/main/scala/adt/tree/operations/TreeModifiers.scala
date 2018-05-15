package adt.tree.operations

import adt.tree.Tree

trait TreeModifiers[A] {
  def prune(subtree: Tree[A]): Tree[A]

  def add(el: A): Tree[A]

  def map[B: Ordering](f: A => B): Tree[B]

  def swap(k: Int): Tree[A]

  def swapAll(ks: List[Int]): Tree[A]

  def filter(f: A => Boolean): List[A]

  def filterNot(f: A => Boolean): List[A]

  def merge(tree: Tree[A]): Tree[A]

  def addAll(elements: List[A]): Tree[A]
}
