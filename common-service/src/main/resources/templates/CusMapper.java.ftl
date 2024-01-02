package ${package.Mapper};

import ${package.Entity}.${entity};
{superMapperClassPackage};
<#if mapperAnnotationClass??>{mapperAnnotationClass.name};
</#if>

/**
* <p>
    * ${table.comment!} Mapper 接口
    * </p>
*
* @author ${author}
* @since ${date}
*/
<#if mapperAnnotationClass??>
    @${mapperAnnotationClass.simpleName}
</#if>
<#if kotlin>
    interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
    public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {
        /**
        * insert record to table selective
        *
        * @param param the record
        * @return insert count
        */
        int insertSelective(${entity} param);

        /**
        * update record selective
        *
        * @param param the updated record
        * @return update count
        */
        int updateByPrimaryKeySelective(${entity} param);

        /**
        * select（by ID）
        *
        * @param id id
        * @return ${entity}
        */
        ${entity} selectByPrimaryKey(@Param("id") Long id);

        /**
        * del（By id）
        *
        * @param id id
        * @return delete count
        */
        int deleteByPrimaryKey(@Param("id") Long id);
    }
</#if>