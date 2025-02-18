module algebra

class functor_t {
  C: category_t
  D: category_t

  map(a: C.object_t): D.object_t
  fmap(f: C.morphism_t(a, b)): D.morphism_t(map(a), map(b))

  fmap_respect_then(
    f: C.morphism_t(a, b),
    g: C.morphism_t(b, c),
  ): eqv(fmap(C.compose(f, g)), D.compose(fmap(f), fmap(g)))

  fmap_respect_id(
    a: C.object_t
  ): eqv(fmap(C.id(a)), D.id(map(a)))
}
