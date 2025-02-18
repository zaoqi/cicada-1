module algebra

class natural_transformation_t {
  C: category_t
  D: category_t

  F: functor_t(C, D)
  G: functor_t(C, D)

  component(a: C.object_t): D.morphism_t(F.map(a), G.map(a))

  natural(
    f: F.morphism_t(a, b)
  ): eqv(
    D.compose(component(a), G.fmap(f)),
    D.compose(F.fmap(f), component(b)))

  note {
    component(a) : D.morphism_t(F.map(a), G.map(a))
    G.fmap(f)    : D.morphism_t(G.map(a), G.map(b))

    F.fmap(f)    : D.morphism_t(F.map(a), F.map(b))
    component(b) : D.morphism_t(F.map(b), G.map(b))
  }
}
