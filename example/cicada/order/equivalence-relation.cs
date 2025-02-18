module order

class equivalence_relation_t extends pre_order_t {
  equivalence_t(a: E, b: E): type_t = pre_t(a, b)

  symmetric(equivalence_t(a, b)): equivalence_t(b, a)
}
