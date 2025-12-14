package io.github.hvogel.clientes.model.specification;

import io.github.hvogel.clientes.criteria.SearchCriteria;
import io.github.hvogel.clientes.enums.SearchOperation;
import io.github.hvogel.clientes.model.entity.Prestador;
import jakarta.persistence.criteria.*;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PrestadorSpecificationTest {

    @Mock
    private Root<Prestador> root;

    @Mock
    private CriteriaQuery<?> query;

    @Mock
    private CriteriaBuilder criteriaBuilder;

    @Mock
    private EntityType<Prestador> entityType;

    @Mock
    private SingularAttribute<Prestador, ?> attribute;

    @Mock
    private Path<Object> path;

    @Mock
    private Expression<String> stringExpression;

    @Mock
    private Expression<LocalDate> dateExpression;

    @Mock
    private Expression<LocalDateTime> dateTimeExpression;

    @Mock
    private Predicate predicate;

    private PrestadorSpecification specification;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        specification = new PrestadorSpecification();
    }

    @Test
    void testAdd() {
        SearchCriteria criteria = new SearchCriteria("nome", "Test", SearchOperation.EQUAL);
        specification.add(criteria);
        assertNotNull(specification);
    }

    @Test
    void testHasNomeLike() {
        var spec = PrestadorSpecification.hasNomeLike("João");
        assertNotNull(spec);
    }

    @Test
    void testToPredicateEqual() {
        when(root.getModel()).thenReturn(entityType);
        when(entityType.getAttribute("nome")).thenReturn((SingularAttribute) attribute);
        when(attribute.getJavaType()).thenReturn((Class) String.class);
        when(root.get("nome")).thenReturn((Path) path);
        when(criteriaBuilder.equal(any(), any())).thenReturn(predicate);
        when(criteriaBuilder.and(any(Predicate[].class))).thenReturn(predicate);

        SearchCriteria criteria = new SearchCriteria("nome", "João", SearchOperation.EQUAL);
        specification.add(criteria);

        Predicate result = specification.toPredicate(root, query, criteriaBuilder);

        assertNotNull(result);
        verify(criteriaBuilder, atLeastOnce()).equal(any(), anyString());
    }

    @Test
    void testToPredicateLike() {
        when(root.getModel()).thenReturn(entityType);
        when(entityType.getAttribute("nome")).thenReturn((SingularAttribute) attribute);
        when(attribute.getJavaType()).thenReturn((Class) String.class);
        when(root.get("nome")).thenReturn((Path) path);
        when(path.as(String.class)).thenReturn((Expression) stringExpression);
        when(criteriaBuilder.lower(any(Expression.class))).thenReturn(stringExpression);
        when(criteriaBuilder.like(any(Expression.class), anyString())).thenReturn(predicate);
        when(criteriaBuilder.and(any(Predicate[].class))).thenReturn(predicate);

        SearchCriteria criteria = new SearchCriteria("nome", "João", SearchOperation.LIKE);
        specification.add(criteria);

        Predicate result = specification.toPredicate(root, query, criteriaBuilder);

        assertNotNull(result);
        verify(criteriaBuilder).like(any(Expression.class), anyString());
    }

    @Test
    void testToPredicateLikeStart() {
        when(root.getModel()).thenReturn(entityType);
        when(entityType.getAttribute("nome")).thenReturn((SingularAttribute) attribute);
        when(attribute.getJavaType()).thenReturn((Class) String.class);
        when(root.get("nome")).thenReturn((Path) path);
        when(path.as(String.class)).thenReturn((Expression) stringExpression);
        when(criteriaBuilder.lower(any(Expression.class))).thenReturn(stringExpression);
        when(criteriaBuilder.like(any(Expression.class), anyString())).thenReturn(predicate);
        when(criteriaBuilder.and(any(Predicate[].class))).thenReturn(predicate);

        SearchCriteria criteria = new SearchCriteria("nome", "João", SearchOperation.LIKE_START);
        specification.add(criteria);

        Predicate result = specification.toPredicate(root, query, criteriaBuilder);

        assertNotNull(result);
        verify(criteriaBuilder).like(any(Expression.class), contains("joão"));
    }

    @Test
    void testToPredicateLikeEnd() {
        when(root.getModel()).thenReturn(entityType);
        when(entityType.getAttribute("nome")).thenReturn((SingularAttribute) attribute);
        when(attribute.getJavaType()).thenReturn((Class) String.class);
        when(root.get("nome")).thenReturn((Path) path);
        when(path.as(String.class)).thenReturn((Expression) stringExpression);
        when(criteriaBuilder.lower(any(Expression.class))).thenReturn(stringExpression);
        when(criteriaBuilder.like(any(Expression.class), anyString())).thenReturn(predicate);
        when(criteriaBuilder.and(any(Predicate[].class))).thenReturn(predicate);

        SearchCriteria criteria = new SearchCriteria("nome", "João", SearchOperation.LIKE_END);
        specification.add(criteria);

        Predicate result = specification.toPredicate(root, query, criteriaBuilder);

        assertNotNull(result);
        verify(criteriaBuilder).like(any(Expression.class), contains("%joão"));
    }

    @Test
    void testToPredicateNotEqual() {
        when(root.getModel()).thenReturn(entityType);
        when(entityType.getAttribute("nome")).thenReturn((SingularAttribute) attribute);
        when(attribute.getJavaType()).thenReturn((Class) String.class);
        when(root.get("nome")).thenReturn((Path) path);
        when(criteriaBuilder.notEqual(any(), any())).thenReturn(predicate);
        when(criteriaBuilder.and(any(Predicate[].class))).thenReturn(predicate);

        SearchCriteria criteria = new SearchCriteria("nome", "João", SearchOperation.NOT_EQUAL);
        specification.add(criteria);

        Predicate result = specification.toPredicate(root, query, criteriaBuilder);

        assertNotNull(result);
        verify(criteriaBuilder, atLeastOnce()).notEqual(any(), anyString());
    }

    @Test
    void testToPredicateGreaterThan() {
        when(root.getModel()).thenReturn(entityType);
        when(entityType.getAttribute("id")).thenReturn((SingularAttribute) attribute);
        when(attribute.getJavaType()).thenReturn((Class) Integer.class);
        when(root.get("id")).thenReturn((Path) path);
        when(path.as(any(Class.class))).thenReturn((Expression) path);
        when(criteriaBuilder.greaterThan(any(Expression.class), any(Comparable.class))).thenReturn(predicate);
        when(criteriaBuilder.and(any(Predicate[].class))).thenReturn(predicate);

        SearchCriteria criteria = new SearchCriteria("id", "10", SearchOperation.GREATER_THAN);
        specification.add(criteria);

        Predicate result = specification.toPredicate(root, query, criteriaBuilder);

        assertNotNull(result);
        verify(criteriaBuilder).greaterThan(any(Expression.class), any(Comparable.class));
    }

    @Test
    void testToPredicateGreaterThanWithDataCadastro() {
        when(root.getModel()).thenReturn(entityType);
        when(entityType.getAttribute("dataCadastro")).thenReturn((SingularAttribute) attribute);
        when(attribute.getJavaType()).thenReturn((Class) LocalDate.class);
        when(root.get("dataCadastro")).thenReturn((Path) path);
        when(path.as(LocalDate.class)).thenReturn((Expression) dateExpression);
        when(criteriaBuilder.greaterThan(any(Expression.class), any(LocalDate.class))).thenReturn(predicate);
        when(criteriaBuilder.and(any(Predicate[].class))).thenReturn(predicate);

        SearchCriteria criteria = new SearchCriteria("dataCadastro", "01/01/2024", SearchOperation.GREATER_THAN);
        specification.add(criteria);

        Predicate result = specification.toPredicate(root, query, criteriaBuilder);

        assertNotNull(result);
        verify(criteriaBuilder).greaterThan(any(Expression.class), any(LocalDate.class));
    }

    @Test
    void testToPredicateLessThan() {
        when(root.getModel()).thenReturn(entityType);
        when(entityType.getAttribute("id")).thenReturn((SingularAttribute) attribute);
        when(attribute.getJavaType()).thenReturn((Class) Integer.class);
        when(root.get("id")).thenReturn((Path) path);
        when(path.as(any(Class.class))).thenReturn((Expression) path);
        when(criteriaBuilder.lessThan(any(Expression.class), any(Comparable.class))).thenReturn(predicate);
        when(criteriaBuilder.and(any(Predicate[].class))).thenReturn(predicate);

        SearchCriteria criteria = new SearchCriteria("id", "10", SearchOperation.LESS_THAN);
        specification.add(criteria);

        Predicate result = specification.toPredicate(root, query, criteriaBuilder);

        assertNotNull(result);
        verify(criteriaBuilder).lessThan(any(Expression.class), any(Comparable.class));
    }

    @Test
    void testToPredicateLessThanWithDataCadastro() {
        when(root.getModel()).thenReturn(entityType);
        when(entityType.getAttribute("dataCadastro")).thenReturn((SingularAttribute) attribute);
        when(attribute.getJavaType()).thenReturn((Class) LocalDate.class);
        when(root.get("dataCadastro")).thenReturn((Path) path);
        when(path.as(LocalDate.class)).thenReturn((Expression) dateExpression);
        when(criteriaBuilder.lessThan(any(Expression.class), any(LocalDate.class))).thenReturn(predicate);
        when(criteriaBuilder.and(any(Predicate[].class))).thenReturn(predicate);

        SearchCriteria criteria = new SearchCriteria("dataCadastro", "31-12-2024", SearchOperation.LESS_THAN);
        specification.add(criteria);

        Predicate result = specification.toPredicate(root, query, criteriaBuilder);

        assertNotNull(result);
        verify(criteriaBuilder).lessThan(any(Expression.class), any(LocalDate.class));
    }

    @Test
    void testToPredicateGreaterThanEqual() {
        when(root.getModel()).thenReturn(entityType);
        when(entityType.getAttribute("id")).thenReturn((SingularAttribute) attribute);
        when(attribute.getJavaType()).thenReturn((Class) Integer.class);
        when(root.get("id")).thenReturn((Path) path);
        when(path.as(any(Class.class))).thenReturn((Expression) path);
        when(criteriaBuilder.greaterThanOrEqualTo(any(Expression.class), any(Comparable.class))).thenReturn(predicate);
        when(criteriaBuilder.and(any(Predicate[].class))).thenReturn(predicate);

        SearchCriteria criteria = new SearchCriteria("id", "10", SearchOperation.GREATER_THAN_EQUAL);
        specification.add(criteria);

        Predicate result = specification.toPredicate(root, query, criteriaBuilder);

        assertNotNull(result);
        verify(criteriaBuilder).greaterThanOrEqualTo(any(Expression.class), any(Comparable.class));
    }

    @Test
    void testToPredicateLessThanEqual() {
        when(root.getModel()).thenReturn(entityType);
        when(entityType.getAttribute("id")).thenReturn((SingularAttribute) attribute);
        when(attribute.getJavaType()).thenReturn((Class) Integer.class);
        when(root.get("id")).thenReturn((Path) path);
        when(path.as(any(Class.class))).thenReturn((Expression) path);
        when(criteriaBuilder.lessThanOrEqualTo(any(Expression.class), any(Comparable.class))).thenReturn(predicate);
        when(criteriaBuilder.and(any(Predicate[].class))).thenReturn(predicate);

        SearchCriteria criteria = new SearchCriteria("id", "10", SearchOperation.LESS_THAN_EQUAL);
        specification.add(criteria);

        Predicate result = specification.toPredicate(root, query, criteriaBuilder);

        assertNotNull(result);
        verify(criteriaBuilder).lessThanOrEqualTo(any(Expression.class), any(Comparable.class));
    }

    @Test
    void testToPredicateIn() {
        when(root.getModel()).thenReturn(entityType);
        when(entityType.getAttribute("status")).thenReturn((SingularAttribute) attribute);
        when(attribute.getJavaType()).thenReturn((Class) String.class);
        when(root.get("status")).thenReturn((Path) path);
        CriteriaBuilder.In<Object> inClause = mock(CriteriaBuilder.In.class);
        when(criteriaBuilder.in(any(Expression.class))).thenReturn(inClause);
        when(inClause.value(any())).thenReturn(inClause);
        when(criteriaBuilder.and(any(Predicate[].class))).thenReturn(predicate);

        SearchCriteria criteria = new SearchCriteria("status", "A,I,E", SearchOperation.IN);
        specification.add(criteria);

        Predicate result = specification.toPredicate(root, query, criteriaBuilder);

        assertNotNull(result);
        verify(inClause, times(3)).value(anyString());
    }

    @Test
    void testToPredicateNotIn() {
        when(root.getModel()).thenReturn(entityType);
        when(entityType.getAttribute("status")).thenReturn((SingularAttribute) attribute);
        when(attribute.getJavaType()).thenReturn((Class) String.class);
        when(root.get("status")).thenReturn((Path) path);
        CriteriaBuilder.In<Object> inClause = mock(CriteriaBuilder.In.class);
        when(criteriaBuilder.in(any(Expression.class))).thenReturn(inClause);
        when(inClause.value(any())).thenReturn(inClause);
        when(criteriaBuilder.not(any(Predicate.class))).thenReturn(predicate);
        when(criteriaBuilder.and(any(Predicate[].class))).thenReturn(predicate);

        SearchCriteria criteria = new SearchCriteria("status", "C", SearchOperation.NOT_IN);
        specification.add(criteria);

        Predicate result = specification.toPredicate(root, query, criteriaBuilder);

        assertNotNull(result);
        verify(criteriaBuilder).not(any(Predicate.class));
    }

    @Test
    void testToPredicateBetweenDate() {
        when(root.getModel()).thenReturn(entityType);
        when(entityType.getAttribute("dataCadastro")).thenReturn((SingularAttribute) attribute);
        when(attribute.getJavaType()).thenReturn((Class) LocalDate.class);
        when(root.get("dataCadastro")).thenReturn((Path) path);
        when(path.as(LocalDate.class)).thenReturn((Expression) dateExpression);
        when(criteriaBuilder.between(any(Expression.class), any(LocalDate.class), any(LocalDate.class))).thenReturn(predicate);
        when(criteriaBuilder.and(any(Predicate[].class))).thenReturn(predicate);

        SearchCriteria criteria = new SearchCriteria("dataCadastro", "01-01-2024;31-12-2024", SearchOperation.BETWEEN_DATE);
        specification.add(criteria);

        Predicate result = specification.toPredicate(root, query, criteriaBuilder);

        assertNotNull(result);
        verify(criteriaBuilder).between(any(Expression.class), any(LocalDate.class), any(LocalDate.class));
    }

    @Test
    void testToPredicateBetweenDateWithSlashFormat() {
        when(root.getModel()).thenReturn(entityType);
        when(entityType.getAttribute("dataCadastro")).thenReturn((SingularAttribute) attribute);
        when(attribute.getJavaType()).thenReturn((Class) LocalDate.class);
        when(root.get("dataCadastro")).thenReturn((Path) path);
        when(path.as(LocalDate.class)).thenReturn((Expression) dateExpression);
        when(criteriaBuilder.between(any(Expression.class), any(LocalDate.class), any(LocalDate.class))).thenReturn(predicate);
        when(criteriaBuilder.and(any(Predicate[].class))).thenReturn(predicate);

        SearchCriteria criteria = new SearchCriteria("dataCadastro", "01/01/2024;31/12/2024", SearchOperation.BETWEEN_DATE);
        specification.add(criteria);

        Predicate result = specification.toPredicate(root, query, criteriaBuilder);

        assertNotNull(result);
        verify(criteriaBuilder).between(any(Expression.class), any(LocalDate.class), any(LocalDate.class));
    }

    @Test
    void testToPredicateBetweenDateWithInvalidFormat() {
        when(root.getModel()).thenReturn(entityType);
        when(entityType.getAttribute("dataCadastro")).thenReturn((SingularAttribute) attribute);
        when(attribute.getJavaType()).thenReturn((Class) LocalDate.class);
        when(root.get("dataCadastro")).thenReturn((Path) path);
        when(criteriaBuilder.and(any(Predicate[].class))).thenReturn(predicate);

        SearchCriteria criteria = new SearchCriteria("dataCadastro", "invalid;invalid", SearchOperation.BETWEEN_DATE);
        specification.add(criteria);

        Predicate result = specification.toPredicate(root, query, criteriaBuilder);

        assertNotNull(result);
    }

    @Test
    void testToPredicateBetweenDateWithSingleValue() {
        when(root.getModel()).thenReturn(entityType);
        when(entityType.getAttribute("dataCadastro")).thenReturn((SingularAttribute) attribute);
        when(attribute.getJavaType()).thenReturn((Class) LocalDate.class);
        when(root.get("dataCadastro")).thenReturn((Path) path);
        when(criteriaBuilder.and(any(Predicate[].class))).thenReturn(predicate);

        SearchCriteria criteria = new SearchCriteria("dataCadastro", "01-01-2024", SearchOperation.BETWEEN_DATE);
        specification.add(criteria);

        Predicate result = specification.toPredicate(root, query, criteriaBuilder);

        assertNotNull(result);
    }

    @Test
    void testToPredicateBetweenDateTime() {
        when(root.getModel()).thenReturn(entityType);
        when(entityType.getAttribute("dataHora")).thenReturn((SingularAttribute) attribute);
        when(attribute.getJavaType()).thenReturn((Class) LocalDateTime.class);
        when(root.get("dataHora")).thenReturn((Path) path);
        when(path.as(LocalDateTime.class)).thenReturn((Expression) dateTimeExpression);
        when(criteriaBuilder.between(any(Expression.class), any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(predicate);
        when(criteriaBuilder.and(any(Predicate[].class))).thenReturn(predicate);

        SearchCriteria criteria = new SearchCriteria("dataHora", "01-01-2024 10:00:00;31-12-2024 18:00:00", SearchOperation.BETWEEN_DATETIME);
        specification.add(criteria);

        Predicate result = specification.toPredicate(root, query, criteriaBuilder);

        assertNotNull(result);
        verify(criteriaBuilder).between(any(Expression.class), any(LocalDateTime.class), any(LocalDateTime.class));
    }

    @Test
    void testToPredicateBetweenDateTimeWithInvalidFormat() {
        when(root.getModel()).thenReturn(entityType);
        when(entityType.getAttribute("dataHora")).thenReturn((SingularAttribute) attribute);
        when(attribute.getJavaType()).thenReturn((Class) LocalDateTime.class);
        when(root.get("dataHora")).thenReturn((Path) path);
        when(criteriaBuilder.and(any(Predicate[].class))).thenReturn(predicate);

        SearchCriteria criteria = new SearchCriteria("dataHora", "invalid;invalid", SearchOperation.BETWEEN_DATETIME);
        specification.add(criteria);

        Predicate result = specification.toPredicate(root, query, criteriaBuilder);

        assertNotNull(result);
    }

    @Test
    void testToPredicateWithInvalidDateFormat() {
        when(root.getModel()).thenReturn(entityType);
        when(entityType.getAttribute("dataCadastro")).thenReturn((SingularAttribute) attribute);
        when(attribute.getJavaType()).thenReturn((Class) LocalDate.class);
        when(root.get("dataCadastro")).thenReturn((Path) path);
        when(criteriaBuilder.and(any(Predicate[].class))).thenReturn(predicate);

        SearchCriteria criteria = new SearchCriteria("dataCadastro", "invaliddate", SearchOperation.EQUAL);
        specification.add(criteria);

        Predicate result = specification.toPredicate(root, query, criteriaBuilder);

        assertNotNull(result);
    }

    @Test
    void testToPredicateWithMultipleCriteria() {
        when(root.getModel()).thenReturn(entityType);
        when(entityType.getAttribute(anyString())).thenReturn((SingularAttribute) attribute);
        when(attribute.getJavaType()).thenReturn((Class) String.class);
        when(root.get(anyString())).thenReturn((Path) path);
        when(criteriaBuilder.equal(any(), any())).thenReturn(predicate);
        when(criteriaBuilder.and(any(Predicate[].class))).thenReturn(predicate);

        SearchCriteria criteria1 = new SearchCriteria("nome", "João", SearchOperation.EQUAL);
        SearchCriteria criteria2 = new SearchCriteria("cpf", "12345678901", SearchOperation.EQUAL);
        specification.add(criteria1);
        specification.add(criteria2);

        Predicate result = specification.toPredicate(root, query, criteriaBuilder);

        assertNotNull(result);
        verify(criteriaBuilder, atLeast(2)).equal(any(), anyString());
    }
}
