package calculator

import calculator.base.BaseConverterTest

internal class RemoveMulConverterTest : BaseConverterTest<Int, AddMulExpr, AddExpr>() {

    override val converter = RemoveMulConverter()

    override val cases: List<Pair<AddMulExpr, AddExpr>> = listOf(

        // original: 3*3
        // expected: 3+(3+3)
        Pair(
            AddMulExpr.Mul(
                AddMulExpr.Num(2),
                AddMulExpr.Num(3)
            ),
            AddExpr.Add(
                AddExpr.Num(3),
                AddExpr.Num(3)
            )
        ),

        // original: 3*3
        // expected: 3+(3+3)
        Pair(
            AddMulExpr.Mul(
                AddMulExpr.Num(3),
                AddMulExpr.Num(3)
            ),
            AddExpr.Add(
                AddExpr.Num(3),
                AddExpr.Add(
                    AddExpr.Num(3),
                    AddExpr.Num(3)
                )
            )
        ),

        // original: (1+3)*2
        // expected: (1+3)+(1+3)
        Pair(
            AddMulExpr.Mul(
                AddMulExpr.Add(
                    AddMulExpr.Num(1),
                    AddMulExpr.Num(3)
                ),
                AddMulExpr.Num(2)
            ),
            AddExpr.Add(
                AddExpr.Add(
                    AddExpr.Num(1),
                    AddExpr.Num(3)
                ),
                AddExpr.Add(
                    AddExpr.Num(1),
                    AddExpr.Num(3)
                )
            )
        ),

        // original: (1+2)*(2+3)
        // expected: 1*(2+3)+2*(2+3)
        //       <=> (2+3)+((2+3)+(2+3))
        Pair(
            AddMulExpr.Mul(
                AddMulExpr.Add(
                    AddMulExpr.Num(1),
                    AddMulExpr.Num(2)
                ),
                AddMulExpr.Add(
                    AddMulExpr.Num(2),
                    AddMulExpr.Num(3)
                )
            ),
            AddExpr.Add(
                AddExpr.Add(
                    AddExpr.Num(2),
                    AddExpr.Num(3)
                ),
                AddExpr.Add(
                    AddExpr.Add(
                        AddExpr.Num(2),
                        AddExpr.Num(3)
                    ),
                    AddExpr.Add(
                        AddExpr.Num(2),
                        AddExpr.Num(3)
                    )
                )
            )
        ),

        // original: (2+3)*(3*5)
        // expected: (2+3)*(5+(5+5))
        //       <=> 2*(5+(5+5)) + 3*(5+(5+5))
        //       <=> ((5+(5+5))+(5+(5+5))) + ((5+(5+5))+((5+(5+5))+(5+(5+5))))
        Pair(
            AddMulExpr.Mul(
                AddMulExpr.Add(
                    AddMulExpr.Num(2),
                    AddMulExpr.Num(3)
                ),
                AddMulExpr.Mul(
                    AddMulExpr.Num(3),
                    AddMulExpr.Num(5)
                )
            ),
            AddExpr.Add(
                AddExpr.Add(
                    AddExpr.Add(
                        AddExpr.Num(5),
                        AddExpr.Add(
                            AddExpr.Num(5),
                            AddExpr.Num(5)
                        )
                    ),
                    AddExpr.Add(
                        AddExpr.Num(5),
                        AddExpr.Add(
                            AddExpr.Num(5),
                            AddExpr.Num(5)
                        )
                    )
                ),
                AddExpr.Add(
                    AddExpr.Add(
                        AddExpr.Num(5),
                        AddExpr.Add(
                            AddExpr.Num(5),
                            AddExpr.Num(5)
                        )
                    ),
                    AddExpr.Add(
                        AddExpr.Add(
                            AddExpr.Num(5),
                            AddExpr.Add(
                                AddExpr.Num(5),
                                AddExpr.Num(5)
                            )
                        ),
                        AddExpr.Add(
                            AddExpr.Num(5),
                            AddExpr.Add(
                                AddExpr.Num(5),
                                AddExpr.Num(5)
                            )
                        )
                    )
                )
            )
        ),

        // original: (1*1)*(1*1)
        // expected: 1
        Pair(
            AddMulExpr.Mul(
                AddMulExpr.Mul(
                    AddMulExpr.Num(1),
                    AddMulExpr.Num(1)
                ),
                AddMulExpr.Mul(
                    AddMulExpr.Num(1),
                    AddMulExpr.Num(1)
                )
            ),
            AddExpr.Num(1)
        )
    )
}
