module algebra

class groupoid_t extends category_t {
  inv(f: morphism_t(a, b)): morphism_t(b, a)

  isomorphic_inv(f: morphism_t(a, b)): isomorphism_t(f, inv(f))
}
