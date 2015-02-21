##Types of Parameters
* Specified parameters have a domain and default value. Are treated as constants for initial solve using default value, can be adjusted.
* Constant parameters have a domain of a single value. Are initialized with a constant value of an expression who will result in constant value.
* Solvable parameters are initialized with an expression.

##Parameters
* parameters must be initialized with a domain and a default value
* specified parameters can have double ranges or integer sets as domains


##Constants
* special type of parameter with a single value as the domain
* anything of type double/integer are considered constant inputs


##Solvable Parameters
* solvable parameters cannot be given a domain and will be solved for
* when a parameter is part of a constructors signature, one can construct with a solvable or specified parameter, or a constant 


##Sketches
* all sketches should be maximally constrained
* when constructing a sketch, translation, rotation are unconstrained