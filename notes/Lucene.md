# Resumen de Lucene

## ¿Qué es Lucene?
Lucene es una **biblioteca de Java de código abierto** que permite **crear motores de búsqueda** personalizados.  
No es un motor completo como Google o Elasticsearch, sino una **herramienta base** para construir uno.

---

## ¿Cómo funciona Lucene?

- Usa una estructura llamada **índice invertido**.
- Un índice invertido es como un **diccionario** que, para cada **término**, indica en **qué documentos** aparece.
- Esto permite **no recorrer todos los documentos** al buscar: simplemente se consulta el índice.

---

## Documentos, Campos y Términos

- **Documento:** unidad de información (por ejemplo, un archivo, una noticia).
- **Campo (Field):** atributo del documento (por ejemplo: `autor`, `fecha`, `contenido`).
- **Término:** palabra o número procesado para búsqueda (tokenizado).

Ejemplo:
- Documento: Receta de cocina
- Campos: `título = "Torta de Manzana"`, `autor = "Leticia Gomez"`
- Términos: `["torta", "manzana", "leticia", "gomez"]`

---

## Almacenamiento e Indexación

Al guardar información en Lucene, podemos:
- **Almacenarla** (sin indexar: no se puede buscar por ella).
- **Indexarla** (se puede buscar, pero no se almacena tal cual).
- **Ambas cosas** (almacenar e indexar).

**Nota:** Los términos se **tokenizan** (se pasan a minúscula, se separan, etc.).

Campo especial: **TextField**, que ya viene configurado para indexar texto común.

---

## Aplicaciones en Lucene: IndexBuilder y Searcher

- **IndexBuilder:** programa que **arma el índice** leyendo documentos de un directorio.
- **Searcher:** programa que **recibe consultas** y **busca en el índice** los documentos coincidentes.

**Importante:** Es responsabilidad del desarrollador **mantener el índice actualizado** (regenerarlo si los documentos cambian).

---

## Opciones de un field


| Opcion                                                | Descripcion                                                                       |
|-------------------------------------------------------|-----------------------------------------------------------------------------------|
| IndexOptions.NONE                                     | no se indexa                                                                      |
| IndexOptions.DOCS_AND_FREQS                           | se indexa cada termino con los doc ids en los que participa y frecuencia en ellos |
| IndexOptions.Docs_AND_FREQS_AND_POSITIONS             | idem y tambien las posiciones ordinales donde se encuentran                       |
| IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS | idem y offsets dentro de las posiciones ordinales                                 |

## Tipos de Consultas (Queries)

- **PrefixQuery:** Buscar palabras que empiecen con cierto prefijo (`"auto*"` → encuentra "automóvil", "autopista").
- **TermRangeQuery:** Buscar términos entre dos valores.
- **PhraseQuery:** Buscar frases exactas (importa el orden de las palabras).
- **WildcardQuery:** Buscar usando comodines `*` (cualquier secuencia) y `?` (un carácter).
- **FuzzyQuery:** Buscar palabras similares (tolerancia a errores de tipeo).
- **BooleanQuery:** Combinar consultas usando `AND`, `OR`, `NOT`.

## Formulas para calcular el *score*
$$
Score(Doc_i, query) = FormulaLocal(Doc_i, term) \cdot FormulaGlobal(D, term)
$$

### Formula local
$$
\sqrt{\frac{freq(term \space in \space Doc_i)}{terminos \space existentes \space in \space Doc_i}}
$$

### Formula Global
$$
1 + ln(\frac{1+docs \space in \space coleccion}{1 + docs \space que \space tienen \space el \space termino})
$$

## Query multi-termino
$$
\text{Score}(\text{DOC}_i, \text{query}) = \sum_{\substack{\text{term} \in \text{query} \\ \text{y no tiene NOT}}} \text{FormulaLocal}(\text{DOC}_i, \text{term}) \times \text{FormulaGlobal}(D, \text{term})
$$


