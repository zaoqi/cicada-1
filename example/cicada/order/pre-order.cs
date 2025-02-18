module order

class pre_order_t {
  E: type_t
  pre_t(E, E): type_t
  reflexive(a: E): pre_t(a, a)
  transitive(pre_t(a, b), pre_t(b, c)): pre_t(a, c)

  strict_pre_t(a: E, b: E): type_t =
    (pre_t(a <= b), not_eqv(a, b))
}

// thin category
// hom_set(A, B).size <= 1
